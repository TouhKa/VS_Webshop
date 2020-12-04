package com.vslab.user;

import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
public interface UserRepo extends CrudRepository<User, Long> {

    User findById(int id);

    List<User> findAll();

    long deleteById(int id);

    User findByName(String name);
}
