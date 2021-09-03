package com.vslab.roles;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class RoleController {

     @Autowired
     private RoleService roleService;

     @GetMapping("/roles/")
     public Role[] getAllRoles() {
          return roleService.getAllRoles();
     }

     @GetMapping("/roles/{id}")
     public Role getRoleById(@PathVariable long id) {
          return roleService.getRoleById(id);
     }

     @PostMapping(value = "/roles/")
     public Role addRole(@RequestBody Role role) {
          return roleService.addRole(role);
     }

     @PutMapping(value = "/roles/")
     public Role updateRole(@RequestBody Role role){
          return roleService.updateRole(role);
     }
     
     @DeleteMapping("/roles/{id}")
     public void deleteRole(@PathVariable int id){
          roleService.deleteRole(id);
     }


}
