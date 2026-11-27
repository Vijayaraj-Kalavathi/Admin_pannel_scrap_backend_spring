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

}