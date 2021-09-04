package com.vslab.webshop;

import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
public interface CategoryRepo extends CrudRepository<Category, Long> {

    Category findById(int id);

    List<Category> findAll();

    long deleteById(int id);

    Category findByName(String name);
}
