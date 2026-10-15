package com.example.ecommerce.Controller;

import com.example.ecommerce.Model.Customer;
import com.example.ecommerce.Model.ScrapType;
import com.example.ecommerce.Service.ScrapType.ScrapTypeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/scraptype")
public class ScrapTypeController{
    private final ScrapTypeService scraptypeservice;

    public ScrapTypeController(ScrapTypeService scraptypeservice){
        this.scraptypeservice = scraptypeservice;
    }

    @PostMapping()
    public ResponseEntity<Map<String, Object>> createScrapType(@RequestBody ScrapType scraptype) {
        ScrapType createScrapType = scraptypeservice.createScrapType(scraptype);

        Map<String, Object> response = new HashMap<>();
        response.put("Message", "Customer Created Successfully");
        response.put("data", createScrapType);

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping
    public List<ScrapType> getAllScrapType() {
        return scraptypeservice.getAllScrapType();
    }

    @GetMapping("/{id}")
    public ScrapType getScrapTypeById(@PathVariable Long id) {
        return scraptypeservice.getScrapTypeById(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Map<String, Object>> updateScrapTypeById(@PathVariable Long id, @RequestBody ScrapType scraptype) {
        ScrapType updateScrapType= scraptypeservice.updateScrapTypeById(id, scraptype);

        Map<String, Object> response = new HashMap<>();
        response.put("message", "Customer Updated Successfully");
        response.put("data",updateScrapType);
        return ResponseEntity.ok(response);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Object>> deleteScrapTypeById(
            @PathVariable Long id) {

        ScrapType deletedScrapType =
                scraptypeservice.deleteScrapTypeById(id);

        Map<String, Object> response = new HashMap<>();

        response.put("Message", "ScrapType deleted successfully");
        response.put("data", deletedScrapType);

        return ResponseEntity.ok(response);
    }
}