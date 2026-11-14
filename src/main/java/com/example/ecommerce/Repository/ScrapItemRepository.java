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
    List<ScrapItem> findByUser_Id(Long userId);

    List<ScrapItem> findByUser_IdAndDate(
            Long userId,
            LocalDate date
    );

    @Query("""
            SELECT item
            FROM ScrapItem item
            WHERE item.user.id = :userId
              AND item.date = :date
              AND item.scrapType.id = :scrapTypeId
              AND item.customer.customer_id = :customerId
            """)
    List<ScrapItem> findScrapItemsByFilters(
            @Param("userId") Long userId,
            @Param("date") LocalDate date,
            @Param("scrapTypeId") Long scrapTypeId,
            @Param("customerId") Long customerId
    );
}