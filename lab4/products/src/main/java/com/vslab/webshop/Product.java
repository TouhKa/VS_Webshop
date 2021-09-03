package com.vslab.webshop;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Product {

    //@Id: indicating the member field below is the primary key of current entity
    @Id
    // auto- incrementing id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String name;
    private double price;
    private int categoryId;
    private String details;

    public Product( String name, double price, int categoryId, String details) {
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

    public double getPrice() {
        return this.price;
    }

    public int getCategoryId() {
        return this.categoryId;
    }

    public String getDetails() {
        return this.details;
    }

}