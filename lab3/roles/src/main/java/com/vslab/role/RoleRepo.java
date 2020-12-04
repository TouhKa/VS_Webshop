package com.vslab.role;

import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
public interface RoleRepo extends CrudRepository<Role, Long> {

    Role findById(int id);

    List<Role> findAll();

    long deleteById(int id);

    Role findByName(String name);
}
