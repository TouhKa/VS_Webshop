package com.vslab.webshop;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class RoleService {

    @Autowired
    private RoleRepo repo;


    public Role[] getAllRoles() {
        List<Role> roles = repo.findAll();
        Role[] allRoles = new Role[roles.size()];
        return roles.toArray(allRoles);
    }

    public Role getRoleById(long id) {
        Optional<Role> optionalRole = repo.findById(id);
        return optionalRole.orElse(null);
    }

    public Role addRole(Role role) {
        return repo.save(role);
    }

    public Role updateRole(Role role) {
        return repo.save(role);
    }

    public void deleteRole(long id) {
        repo.deleteById(id);
    }



}
