package com.example.ecommerce.Model;

import jakarta.persistence.*;

@Entity
@Table(name="scraptype")
public class ScrapType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String scrapName;
    private String description;

    @Column(nullable = false)
    private String status = "ACTIVE";

    public ScrapType(){

    }

    public ScrapType(Long id, String scrapName, String description){
        this.id = id;
        this.scrapName = scrapName;
        this.description = description;
        this.status = "ACTIVE";
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getScrapName() {
        return scrapName;
    }

    public void setScrapName(String scrapName) {
        this.scrapName = scrapName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
