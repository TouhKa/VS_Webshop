package com.vslab.users;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class UserService {

    @Autowired
    private UserRepo repo;


    public User[] getAllUsers() {
        List<User> products = repo.findAll();
        User[] allCategories = new User[products.size()];
        return products.toArray(allCategories);
    }

    public User addUser(User user) {

        return repo.save(user);
    }

    public User getUserById(long id) {
        Optional<User> optionalRole = repo.findById(id);
        return optionalRole.orElse(null);
    }

    public User updateUser(User user) {
        return repo.save(user);
    }

    public void deleteUser(long id) {
        repo.deleteById(id);
    }



}
