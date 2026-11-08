package com.example.ecommerce.Controller;

import com.example.ecommerce.Model.ScrapItem;
import com.example.ecommerce.Model.ScrapType;
import com.example.ecommerce.Service.ScrapItem.ScrapItemService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/scrapitem")
public class ScrapItemController{
    private final ScrapItemService scrapitemservice;

    public ScrapItemController(ScrapItemService scrapitemservice){
        this.scrapitemservice = scrapitemservice;
    }

    @PostMapping()
    public ResponseEntity<Map<String, Object>> createScrapItem(@RequestBody ScrapItem scrapitem) {

        System.out.println("POST /scrapitem controller reached");
        ScrapItem createScrapItem = scrapitemservice.createScrapItem(scrapitem);

        Map<String, Object> response = new HashMap<>();
        response.put("Message", "ScrapItem Created Successfully");
        response.put("data", createScrapItem);

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

//    @GetMapping
//    public List<ScrapItem> getAllScrapItem() {
//        return scrapitemservice.getAllScrapItem();
//    }

    @GetMapping("/user/{userId}")
    public List<ScrapItem> getScrapItemsByUserId(
            @PathVariable Long userId) {

        return scrapitemservice.getScrapItemsByUserId(userId);
    }

    @GetMapping("/{id}")
    public ScrapItem getScrapItemById(@PathVariable Long id) {
        return scrapitemservice.getScrapItemById(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Map<String, Object>> updateScrapItemById(@PathVariable Long id, @RequestBody ScrapItem scrapitem) {
        ScrapItem updateScrapItem= scrapitemservice.updateScrapItemById(id, scrapitem);

        Map<String, Object> response = new HashMap<>();
        response.put("message", "ScrapItem Updated Successfully");
        response.put("data",updateScrapItem);
        return ResponseEntity.ok(response);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Object>> deleteScrapItemById(
            @PathVariable Long id) {

        ScrapItem deletedScrapItem =
                scrapitemservice.deleteScrapItemById(id);

        Map<String, Object> response = new HashMap<>();

        response.put("Message", "ScrapItem deleted successfully");
        response.put("data", deletedScrapItem);

        return ResponseEntity.ok(response);
    }
}