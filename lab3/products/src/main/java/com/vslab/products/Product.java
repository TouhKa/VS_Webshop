package com.vslab.products;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.List;

@Entity
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String name;
    private Double price;
    private Integer categoryId;
    private String details;

    public Product( String name, Double price, Integer categoryId, String details) {
        this.name = name;
        this.price = price;
        this.categoryId = categoryId;
        this.details = details;
    }

    public Product() {

    }

    public int getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public Double getPrice() {
        return this.price;
    }

    public Integer getCategoryId() {
        return this.categoryId;
    }

    public String getDetails() {
        return this.details;
    }

}