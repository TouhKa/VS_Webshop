package com.vslab.webshop.roles;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Role {

    @Id
    @GeneratedValue
    private long id;

    private String type;

    public Role(String type) {
        this.type = type;
    }

    public Role() {
    }

    public long getId() {
        return id;
    }

    public String getType() {
        return type;
    }
}