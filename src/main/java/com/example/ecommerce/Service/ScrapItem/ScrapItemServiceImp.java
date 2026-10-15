package com.example.ecommerce.Service.ScrapItem;

import com.example.ecommerce.Model.ScrapItem;
import com.example.ecommerce.Repository.ScrapItemRepository;
import com.example.ecommerce.Service.ScrapItem.ScrapItemService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ScrapItemServiceImp implements ScrapItemService {

    private final ScrapItemRepository scrapitemrepository;

    public ScrapItemServiceImp(ScrapItemRepository scrapitemrepository){
        this.scrapitemrepository = scrapitemrepository;
    }

    public ScrapItem createScrapItem(ScrapItem scrapitem){
        return scrapitemrepository.save(scrapitem);
    }

    @Override
    public List<ScrapItem> getAllScrapItem(){
        return scrapitemrepository.findAll();
    }

    @Override
    public ScrapItem getScrapItemById(Long id){
        return scrapitemrepository.findById(id).orElseThrow(()-> new RuntimeException("ScrapItem not found"));
    }

    @Override
    public ScrapItem updateScrapItemById(Long id, ScrapItem scrapitem){
        ScrapItem existscrapItem = scrapitemrepository.findById(id).orElseThrow(()-> new RuntimeException("ScrapItem not found"));
        existscrapItem.setDate(scrapitem.getDate());
        existscrapItem.setScrapType(scrapitem.getScrapType());
        existscrapItem.setQuantity(scrapitem.getQuantity());
        existscrapItem.setExporterCompanyName(scrapitem.getExporterCompanyName());
        return  scrapitemrepository.save(existscrapItem);
    }

    @Override
    public ScrapItem deleteScrapItemById(Long id){
        ScrapItem scrapType = scrapitemrepository.findById(id)
                .orElseThrow(() -> new RuntimeException("ScrapItem not found"));

        scrapitemrepository.deleteById(id);

        return scrapType;
    }

}
