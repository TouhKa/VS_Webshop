package com.vslab.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepo userRepo;

    public UserService() {}

    public User[] getAllUsers() {
        List<User> products = userRepo.findAll();
        User[] allCategories = new User[products.size()];
        return products.toArray(allCategories);
    }

    public User addUser(User user) {

        return userRepo.save(user);
    }

    public User getUserById(int id) {
        try {
            return userRepo.findById(id);
        }catch (NullPointerException e){
            return null;
        }
    }

    public User updateUser(User user) {
        return userRepo.save(user);
    }

    public void deleteUser(int id) {
         userRepo.deleteById(id);
    }
}
