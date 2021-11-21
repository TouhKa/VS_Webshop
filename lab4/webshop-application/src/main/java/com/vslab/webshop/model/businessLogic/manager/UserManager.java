package com.vslab.webshop.model.businessLogic.manager;

import com.vslab.webshop.model.data.objects.Role;
import com.vslab.webshop.model.data.objects.User;


public interface UserManager {
    
    void registerUser(String username, String name, String lastname, String password, Role role);
    
    User getUserByUsername(String username);

    User getUserByPasswordCredentials(String username, String password);

    Role getRoleByLevel(int level);
    
    boolean doesUserAlreadyExist(String username);

    User searchUser(User[] users, String password);
}
