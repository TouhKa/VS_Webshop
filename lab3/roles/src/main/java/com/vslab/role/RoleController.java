package com.vslab.role;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class RoleController {

    @Autowired
    private RoleService roleService;

    @GetMapping("/role/")
    public Role[] getAllRoles() {
        return roleService.getAll();
    }

    @PostMapping(value = "/role/", consumes = "application/json")
    public Role addRole(@RequestBody Role role){
        return roleService.addRole(role);
    }

    @GetMapping("/role/{id}")
    public Role getRole(@PathVariable int id){
        return roleService.getRoleById(id);
    }

    @PutMapping(value = "/role/", consumes = "application/json")
    public Role updateRole(@RequestBody Role role){
        return roleService.updateRole(role);
    }
    @DeleteMapping("/role/{id}")
    public void deleteRole(@PathVariable int id){
        roleService.deleteRole(id);
    }
}
