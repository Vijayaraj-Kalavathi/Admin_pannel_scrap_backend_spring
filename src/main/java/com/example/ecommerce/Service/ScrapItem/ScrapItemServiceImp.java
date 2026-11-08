package com.example.ecommerce.Service.ScrapItem;

import com.example.ecommerce.Model.Customer;
import com.example.ecommerce.Model.ScrapItem;
import com.example.ecommerce.Model.ScrapType;
import com.example.ecommerce.Model.User;
import com.example.ecommerce.Repository.CustomerRepository;
import com.example.ecommerce.Repository.ScrapItemRepository;
import com.example.ecommerce.Repository.ScrapTypeRepository;
import com.example.ecommerce.Repository.UserRepository;
import com.example.ecommerce.Service.ScrapItem.ScrapItemService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ScrapItemServiceImp implements ScrapItemService {

    private final ScrapItemRepository scrapitemrepository;
    private final ScrapTypeRepository scraptyperepository;
    private final CustomerRepository customerRepository;
    private final UserRepository userRepository;

    public ScrapItemServiceImp(ScrapItemRepository scrapitemrepository, ScrapTypeRepository scraptyperepository, CustomerRepository customerRepository, UserRepository userRepository) {
        this.customerRepository = customerRepository;
        this.scrapitemrepository = scrapitemrepository;
        this.scraptyperepository = scraptyperepository;
        this.userRepository = userRepository;
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

//    @Override
//    public ScrapItem updateScrapItemById(Long id, ScrapItem scrapitem){
//        ScrapItem existscrapItem = scrapitemrepository.findById(id).orElseThrow(()-> new RuntimeException("ScrapItem not found"));
//        existscrapItem.setDate(scrapitem.getDate());
//        existscrapItem.setScrapType(scrapitem.getScrapType());
//        existscrapItem.setQuantity(scrapitem.getQuantity());
//        existscrapItem.setExporterCompanyName(scrapitem.getExporterCompanyName());
//        return  scrapitemrepository.save(existscrapItem);
//    }

    @Override
    public ScrapItem updateScrapItemById(Long id, ScrapItem scrapitem) {

        // Step 1: Find the existing ScrapItem
        ScrapItem existingScrapItem = scrapitemrepository
                .findById(id)
                .orElseThrow(() ->
                        new RuntimeException("ScrapItem not found with id: " + id));

        // Step 2: Get the ScrapType ID from the request payload
        Long scrapTypeId = scrapitem.getScrapType().getId();

        // Step 3: Find the ScrapType from its own table
        ScrapType existingScrapType = scraptyperepository
                .findById(scrapTypeId)
                .orElseThrow(() ->
                        new RuntimeException("ScrapType not found with id: " + scrapTypeId));

        // Step 4: Update ScrapItem fields
        existingScrapItem.setDate(scrapitem.getDate());

        // Assign the actual ScrapType entity
        existingScrapItem.setScrapType(existingScrapType);

        existingScrapItem.setQuantity(scrapitem.getQuantity());

        // Step 2: Get the Customer ID from the request payload
        Long CustomerId = scrapitem.getCustomer().getCustomer_ID();

        // Step 3: Find the Customer from its own table
        Customer existingCustomer = customerRepository
                .findById(CustomerId)
                .orElseThrow(() ->
                        new RuntimeException("Customer not found with id: " + CustomerId));

        // Assign the actual Customer entity
        existingScrapItem.setCustomer(existingCustomer);

        // Step 2: Get the User ID from the request payload
        Long UserId = scrapitem.getUser().getId();

        // Step 3: Find the User from its own table
        User existingUser = userRepository
                .findById(UserId)
                .orElseThrow(() ->
                        new RuntimeException("User not found with id: " + UserId));

        // Assign the actual User entity
        existingScrapItem.setUser(existingUser);

        // Step 5: Save the updated ScrapItem
        return scrapitemrepository.save(existingScrapItem);
    }

    @Override
    public ScrapItem deleteScrapItemById(Long id){
        ScrapItem scrapType = scrapitemrepository.findById(id)
                .orElseThrow(() -> new RuntimeException("ScrapItem not found"));

        scrapitemrepository.deleteById(id);

        return scrapType;
    }

}
