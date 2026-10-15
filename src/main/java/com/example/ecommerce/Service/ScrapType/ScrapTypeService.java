package com.example.ecommerce.Service.ScrapType;


import com.example.ecommerce.Model.Customer;
import com.example.ecommerce.Model.ScrapType;

import java.util.List;

public interface ScrapTypeService{
    ScrapType createScrapType(ScrapType scraptype);
    List<ScrapType> getAllScrapType();
    ScrapType getScrapTypeById(Long id);
    ScrapType updateScrapTypeById(Long id, ScrapType scraptype);
    ScrapType deleteScrapTypeById(Long id);
}
