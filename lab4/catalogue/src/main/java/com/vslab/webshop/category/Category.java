package com.vslab.webshop.category;

public class Category {

    private int id;
    private String name;
    public Category(){    }

    public Category(int id, String name){
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return this.id;
    }

    public String getName(){
        return this.name;
    }

    public void setName(String name){
        this.name = name;
    }

}
