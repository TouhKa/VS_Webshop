package com.vslab.roles;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class RoleController {

     @Autowired
     private RoleService service;

     @GetMapping("/roles/")
     public Role[] getAllRoles() {
          return service.getAllRoles();
     }

     @GetMapping("/roles/{id}")
     public Role getRoleById(@PathVariable long id) {
          return service.getRoleById(id);
     }

     @PostMapping(value = "/roles/", consumes = "application/json")
     public Role addRole(@RequestBody Role role) {
          return service.addRole(role);
     }

     @PutMapping(value = "/role/", consumes = "application/json")
     public Role updateRole(@RequestBody Role role){
          return service.updateRole(role);
     }
     @DeleteMapping("/role/{id}")
     public void deleteRole(@PathVariable int id){
          service.deleteRole(id);
     }


}
