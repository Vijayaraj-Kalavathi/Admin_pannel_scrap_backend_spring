package com.example.ecommerce.Repository;

import com.example.ecommerce.Model.ScrapType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ScrapTypeRepository extends JpaRepository<ScrapType, Long >{

}