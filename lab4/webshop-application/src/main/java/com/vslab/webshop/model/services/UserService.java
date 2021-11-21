package com.vslab.webshop.model.services;

import com.vslab.webshop.model.data.objects.User;

/**
 * interface for the user microservice
 */
public interface UserService {
    
    void addUser(User user);
    
    User[] getAllUsers();

    User[] getLoginUser(String username, String password);
}
