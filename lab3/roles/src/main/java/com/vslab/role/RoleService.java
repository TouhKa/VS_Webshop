package com.vslab.role;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleService {
    @Autowired
    private RoleRepo roleRepo;

    public RoleService() {}

    public Role[] getAll() {
        List<Role> products = roleRepo.findAll();
        Role[] allCategories = new Role[products.size()];
        return products.toArray(allCategories);
    }

    public Role addRole(Role role) {

        return roleRepo.save(role);
    }

    public Role getRoleById(int id) {
        try {
            return roleRepo.findById(id);
        }catch (NullPointerException e){
            return null;
        }
    }

    public Role updateRole(Role role) {
        return roleRepo.save(role);
    }

    public void deleteRole(int id) {
         roleRepo.deleteById(id);
    }
}
