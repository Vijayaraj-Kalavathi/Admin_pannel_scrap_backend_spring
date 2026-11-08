package com.example.ecommerce.Repository;

import com.example.ecommerce.Model.ScrapItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ScrapItemRepository extends JpaRepository<ScrapItem, Long > {
    List<ScrapItem> findByUser_Id(Long userId);
}