package com.example.ecommerce.Repository;

import com.example.ecommerce.Model.ScrapType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ScrapTypeRepository extends JpaRepository<ScrapType, Long >{

    List<ScrapType> findByStatus(String status);

    List<ScrapType> findByStatusOrderByIdDesc(String status);

    Optional<ScrapType> findByIdAndStatus(Long id, String status);

    boolean existsByScrapNameIgnoreCase(String scrapName);
}