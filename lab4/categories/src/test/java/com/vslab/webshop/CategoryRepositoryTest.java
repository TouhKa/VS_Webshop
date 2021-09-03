package com.vslab.webshop;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
public class CategoryRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;


    @Test
    public void testSaveProduct() {
        String cat_name = "Coffee";
        Category new_cat = entityManager.persist(new Category(cat_name));
        assertTrue(new_cat.getId()==1);
    }

    @Test
    public void testSaveProduct2() {
        String cat_name = "Coffee";
        Category new_cat = entityManager.persist(new Category(cat_name));
        assertTrue(new_cat.getName() == cat_name);
    }

    @Test
    public void testUpdateProduct() {
        String cat_name = "Tea";
        Category new_cat = entityManager.persist(new Category(cat_name));
        new_cat.setName(cat_name);
        assertTrue(new_cat.getName()==cat_name);
    }
}
