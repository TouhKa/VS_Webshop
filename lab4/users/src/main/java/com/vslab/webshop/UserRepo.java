package com.vslab.webshop;

import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
public interface UserRepo extends CrudRepository<User, Long> {

    @Override
    List<User> findAll();
}
