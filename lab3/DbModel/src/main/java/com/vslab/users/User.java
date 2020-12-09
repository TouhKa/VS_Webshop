package com.vslab.users;

import com.vslab.roles.Role;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class User {

    @Id
    @GeneratedValue
    private long id;

    private Role role;

    private String firstname;

    private String lastname;

    private String username;

    private String password;

    public User(Role role, String firstname, String lastname, String username, String password) {
        this.role = role;
        this.firstname = firstname;
        this.lastname = lastname;
        this.username = username;
        this.password = password;
    }

    public long getId() {
        return id;
    }

    public Role getRole() {
        return role;
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