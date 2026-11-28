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

    // CREATE
    // CREATE
    @Override
    public ScrapType createScrapType(ScrapType scraptype) {

        // Validate empty name
        if (scraptype.getScrapName() == null ||
                scraptype.getScrapName().isBlank()) {

            throw new IllegalArgumentException("Scrap Name is required");
        }

        // Check duplicate
        boolean exists = scraptyperepository
                .existsByScrapNameIgnoreCase(
                        scraptype.getScrapName().trim()
                );

        if (exists) {
            throw new IllegalArgumentException("Scrap Type Already Present");
        }

        scraptype.setScrapName(scraptype.getScrapName().trim());
        scraptype.setStatus("ACTIVE");

        return scraptyperepository.save(scraptype);
    }

    // GET ALL ACTIVE SCRAP TYPES - LATEST FIRST
    @Override
    public List<ScrapType> getAllScrapType() {

        return scraptyperepository.findByStatusOrderByIdDesc("ACTIVE");
    }

    // GET BY ID (ACTIVE ONLY)
    @Override
    public ScrapType getScrapTypeById(Long id) {

        return scraptyperepository.findByIdAndStatus(id, "ACTIVE")
                .orElseThrow(() ->
                        new RuntimeException("Active ScrapType not found"));
    }

    // UPDATE
    @Override
    public ScrapType updateScrapTypeById(Long id, ScrapType scraptype) {

        ScrapType existscrapType = scraptyperepository
                .findByIdAndStatus(id, "ACTIVE")
                .orElseThrow(() ->
                        new RuntimeException("Active ScrapType not found"));

        existscrapType.setScrapName(scraptype.getScrapName());
        existscrapType.setDescription(scraptype.getDescription());

        return scraptyperepository.save(existscrapType);
    }

    // SOFT DELETE
    @Override
    public ScrapType deleteScrapTypeById(Long id) {

        ScrapType scrapType = scraptyperepository
                .findByIdAndStatus(id, "ACTIVE")
                .orElseThrow(() ->
                        new RuntimeException("Active ScrapType not found"));

        scrapType.setStatus("INACTIVE");

        return scraptyperepository.save(scrapType);
    }

}
