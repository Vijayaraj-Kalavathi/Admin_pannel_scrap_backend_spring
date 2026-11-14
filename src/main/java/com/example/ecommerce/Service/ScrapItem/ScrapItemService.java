package com.example.ecommerce.Service.ScrapItem;

import com.example.ecommerce.Model.ScrapItem;
import com.example.ecommerce.Model.ScrapItemFilter;
import com.example.ecommerce.Model.ScrapModel.ScrapItemAll;


import java.time.LocalDate;
import java.util.List;

public interface ScrapItemService{
    ScrapItem createScrapItem(ScrapItem scrapitem);
    List<ScrapItem> getScrapItemsByUserId(Long userId);
    List<ScrapItem> getAllScrapItem();
    ScrapItem getScrapItemById(Long id);
    ScrapItem updateScrapItemById(Long id, ScrapItem scrapitem);
    ScrapItem deleteScrapItemById(Long id);
    List<ScrapItem> getScrapTypeFilter(ScrapItemFilter request);
    List<ScrapItem> getScrapItemsUserIdAndDate(ScrapItemAll request);
}
