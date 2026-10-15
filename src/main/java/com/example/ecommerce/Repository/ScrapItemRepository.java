package com.example.ecommerce.Repository;

import com.example.ecommerce.Model.ScrapItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ScrapItemRepository extends JpaRepository<ScrapItem, Long > {

}