package com.vslab.webshop;

import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
public interface RoleRepo extends CrudRepository<Role, Long> {

    @Override
    List<Role> findAll();
}
