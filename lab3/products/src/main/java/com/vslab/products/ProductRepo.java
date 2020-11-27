package com.vslab.products;
import org.springframework.data.repository.CrudRepository;
import javax.transaction.Transactional;
import java.util.List;

@Transactional
public interface ProductRepo extends CrudRepository<Product, Long> {
    Product findByName(String name);

    Product findById(int id);

    List<Product> findAll();

    long deleteById(int id);

    long deleteByName(String name);

}
