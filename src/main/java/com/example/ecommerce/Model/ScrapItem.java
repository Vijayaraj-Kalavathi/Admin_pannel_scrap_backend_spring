package com.example.ecommerce.Model;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "scrapitem")
public class ScrapItem{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Temporal(TemporalType.DATE)
    private Date date;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "scraptype_id", referencedColumnName = "id")
    private ScrapType scrapType;

    private float quantity;
    private String exporterCompanyName;

    public ScrapItem(){}

     public ScrapItem(Long id,Date date, ScrapType scrapType, float quantity, String exporterCompanyName){
        this.id = id;
        this.date = date;
        this.scrapType = scrapType;
        this.quantity = quantity;
        this.exporterCompanyName = exporterCompanyName;
     }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Date getDate() {
         return date;
     }

     public void setDate(Date date) {
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
