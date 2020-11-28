package com.vslab.catalogue;

public class Product {
    private int id;
    private String name;
    private double price;
    private int categoryId;
    private String details;

    public Product() {    }

    public Product( int id, String name, double price, int categoryId, String details) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.categoryId = categoryId;
        this.details = details;
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
