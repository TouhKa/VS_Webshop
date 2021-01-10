package com.vslab.users;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class User {

    @Id
    @GeneratedValue
    private long id;

    private long roleId;

    private String firstname;

    private String lastname;

    private String username;

    private String password;

    public User(long roleId, String firstname, String lastname, String username, String password) {
        this.roleId = roleId;
        this.firstname = firstname;
        this.lastname = lastname;
        this.username = username;
        this.password = password;
    }

    public User(long id, long roleId, String firstname, String lastname, String username, String password) {
        this.id = id;
        this.roleId = roleId;
        this.firstname = firstname;
        this.lastname = lastname;
        this.username = username;
        this.password = password;
    }

    public User() {
    }

    public long getId() {
        return id;
    }

    public long getRoleId() {
        return roleId;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}