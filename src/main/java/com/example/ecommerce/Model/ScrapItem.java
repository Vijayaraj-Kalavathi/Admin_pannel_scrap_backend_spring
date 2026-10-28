package com.example.ecommerce.Model;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "scrapitem")
public class ScrapItem{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate date;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "scraptype_id", referencedColumnName = "id")
    private ScrapType scrapType;

    private float quantity;
    private String exporterCompanyName;

    public ScrapItem(){}

     public ScrapItem(Long id, LocalDate date, ScrapType scrapType, float quantity, String exporterCompanyName){
        this.id = id;
        this.date = date;
        this.scrapType = scrapType;
        this.quantity = quantity;
        this.exporterCompanyName = exporterCompanyName;
     }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getDate() {
         return date;
     }

     public void setDate(LocalDate date) {
         this.date = date;
     }

     public ScrapType getScrapType() {
         return scrapType;
     }

     public void setScrapType(ScrapType scrapType) {
         this.scrapType = scrapType;
     }

     public float getQuantity() {
         return quantity;
     }

     public void setQuantity(float quantity) {
         this.quantity = quantity;
     }

     public String getExporterCompanyName() {
         return exporterCompanyName;
     }

     public void setExporterCompanyName(String exporterCompanyName) {
         this.exporterCompanyName = exporterCompanyName;
     }
 }
