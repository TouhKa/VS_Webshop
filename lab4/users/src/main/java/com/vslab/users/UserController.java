package com.vslab.users;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {

     @Autowired
     private UserService userService;

     @GetMapping("/users/")
     public User[] getAllUsers() {
          return userService.getAllUsers();
     }

     @PostMapping(value = "/users/", consumes = "application/json")
     public User addUser(@RequestBody User user){
          return userService.addUser(user);
     }

     @GetMapping("/users/{id}")
     public User getUser(@PathVariable int id){
          return userService.getUserById(id);
     }

     @PutMapping(value = "/users/", consumes = "application/json")
     public User updateUser(@RequestBody User user){
          return userService.updateUser(user);
     }
     @DeleteMapping("/users/{id}")
     public void deleteUser(@PathVariable int id){
          userService.deleteUser(id);
     }


}
