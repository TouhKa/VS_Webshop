package com.vslab.webshop;

import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
public interface ProductRepo extends CrudRepository<Product, Long> {

    Product findById(int id);

    List<Product> findAll();

    long deleteById(int id);
}
