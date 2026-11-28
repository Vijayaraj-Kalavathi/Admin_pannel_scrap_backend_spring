package com.example.ecommerce.Repository;

import com.example.ecommerce.Model.ScrapItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ScrapItemRepository extends JpaRepository<ScrapItem, Long > {
//    List<ScrapItem> findByUser_Id(Long userId);
//
//    List<ScrapItem> findByUser_IdAndDate(
//            Long userId,
//            LocalDate date
//    );
    // Get all - latest first
    List<ScrapItem> findAllByOrderByIdDesc();

    // Get by User ID - latest first
    List<ScrapItem> findByUser_IdOrderByIdDesc(Long userId);

    // Get by User ID and Date - latest first
    List<ScrapItem> findByUser_IdAndDateOrderByIdDesc(
            Long userId,
            LocalDate date
    );

    @Query("""
    SELECT item
    FROM ScrapItem item
    JOIN FETCH item.scrapType
    WHERE item.user.id = :userId
      AND item.date = :date
      AND (
          :scrapTypeId IS NULL
          OR :scrapTypeId = 0
          OR item.scrapType.id = :scrapTypeId
      )
      AND (
          :customerId IS NULL
          OR :customerId = 0
          OR item.customer.customer_id = :customerId
      )
    ORDER BY item.id DESC
    """)
    List<ScrapItem> findScrapItemsByFilters(
            @Param("userId") Long userId,
            @Param("date") LocalDate date,
            @Param("scrapTypeId") Long scrapTypeId,
            @Param("customerId") Long customerId
    );

    // 1. Total quantity
    @Query("""
        SELECT COALESCE(SUM(s.quantity), 0)
        FROM ScrapItem s
        WHERE s.user.id = :userId
        AND s.date BETWEEN :fromDate AND :toDate
        AND (:customerId IS NULL OR s.customer.customer_id = :customerId)
    """)
    Double getTotalQuantity(
            @Param("userId") Long userId,
            @Param("fromDate") LocalDate fromDate,
            @Param("toDate") LocalDate toDate,
            @Param("customerId") Long customerId
    );


    // 2. Scrap type-wise count and quantity
    @Query("""
        SELECT new com.example.ecommerce.Model.Dashboard.ScrapTypeSummary(
            s.scrapType.scrapName,
            COUNT(s),
            SUM(s.quantity)
        )
        FROM ScrapItem s
        WHERE s.user.id = :userId
        AND s.date BETWEEN :fromDate AND :toDate
        AND (:customerId IS NULL OR s.customer.customer_id = :customerId)
        GROUP BY s.scrapType.id, s.scrapType.scrapName
        ORDER BY SUM(s.quantity) DESC
    """)
    List<com.example.ecommerce.Model.Dashboard.ScrapTypeSummary> getScrapTypeSummary(
            @Param("userId") Long userId,
            @Param("fromDate") LocalDate fromDate,
            @Param("toDate") LocalDate toDate,
            @Param("customerId") Long customerId
    );


    // 3. Distinct exporters
    @Query("""
        SELECT COUNT(DISTINCT s.customer.customer_id)
        FROM ScrapItem s
        WHERE s.user.id = :userId
        AND s.date BETWEEN :fromDate AND :toDate
        AND (:customerId IS NULL OR s.customer.customer_id = :customerId)
    """)
    Long getActiveExporterCount(
            @Param("userId") Long userId,
            @Param("fromDate") LocalDate fromDate,
            @Param("toDate") LocalDate toDate,
            @Param("customerId") Long customerId
    );


    // 4. Customer-wise dispatch count
    @Query("""
        SELECT new com.example.ecommerce.Model.Dashboard.CustomerDispatch(
            s.customer.customer_id,
            s.customer.customer_name,
            COUNT(s),
            SUM(s.quantity)
        )
        FROM ScrapItem s
        WHERE s.user.id = :userId
        AND s.date BETWEEN :fromDate AND :toDate
        AND (:customerId IS NULL OR s.customer.customer_id = :customerId)
        GROUP BY s.customer.customer_id, s.customer.customer_name
        ORDER BY COUNT(s) DESC
    """)
    List<com.example.ecommerce.Model.Dashboard.CustomerDispatch> getCustomerDispatch(
            @Param("userId") Long userId,
            @Param("fromDate") LocalDate fromDate,
            @Param("toDate") LocalDate toDate,
            @Param("customerId") Long customerId
    );


    // 5. Month-wise dispatch count
    @Query("""
        SELECT new com.example.ecommerce.Model.Dashboard.MonthlyDispatch(
            CONCAT(
                CAST(YEAR(s.date) AS string),
                '-',
                CASE WHEN MONTH(s.date) < 10
                     THEN CONCAT('0', CAST(MONTH(s.date) AS string))
                     ELSE CAST(MONTH(s.date) AS string)
                END
            ),
            COUNT(s),
            SUM(s.quantity)
        )
        FROM ScrapItem s
        WHERE s.user.id = :userId
        AND s.date BETWEEN :fromDate AND :toDate
        AND (:customerId IS NULL OR s.customer.customer_id = :customerId)
        GROUP BY YEAR(s.date), MONTH(s.date)
        ORDER BY YEAR(s.date), MONTH(s.date)
    """)
    List<com.example.ecommerce.Model.Dashboard.MonthlyDispatch> getMonthlyDispatch(
            @Param("userId") Long userId,
            @Param("fromDate") LocalDate fromDate,
            @Param("toDate") LocalDate toDate,
            @Param("customerId") Long customerId
    );

}