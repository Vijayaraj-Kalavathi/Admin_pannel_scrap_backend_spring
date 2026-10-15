package com.example.ecommerce.Service.ScrapType;

import com.example.ecommerce.Model.Customer;
import com.example.ecommerce.Model.ScrapType;
import com.example.ecommerce.Repository.ScrapTypeRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ScrapTypeServiceImp implements ScrapTypeService{

    private final ScrapTypeRepository scraptyperepository;

    public ScrapTypeServiceImp(ScrapTypeRepository scraptyperepository){
        this.scraptyperepository = scraptyperepository;
    }

    public ScrapType createScrapType(ScrapType scraptype){
        return scraptyperepository.save(scraptype);
    }

    @Override
    public List<ScrapType> getAllScrapType(){
        return scraptyperepository.findAll();
    }

    @Override
    public ScrapType getScrapTypeById(Long id){
        return scraptyperepository.findById(id).orElseThrow(()-> new RuntimeException("ScrapType not found"));
    }

    @Override
    public ScrapType updateScrapTypeById(Long id, ScrapType scraptype){
        ScrapType existscrapType = scraptyperepository.findById(id).orElseThrow(()-> new RuntimeException("ScrapType not found"));
        existscrapType.setScrapName(scraptype.getScrapName());
        existscrapType.setDescription(scraptype.getDescription());
        return  scraptyperepository.save(existscrapType);
    }

    @Override
    public ScrapType deleteScrapTypeById(Long id){
        ScrapType scrapType = scraptyperepository.findById(id)
                .orElseThrow(() -> new RuntimeException("ScrapType not found"));

        scraptyperepository.deleteById(id);

        return scrapType;
    }

}
