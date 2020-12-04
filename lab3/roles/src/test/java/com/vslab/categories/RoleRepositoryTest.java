package com.vslab.categories;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.vslab.role.Role;

@DataJpaTest
public class RoleRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;


    @Test
    public void testSaveProduct() {
        String cat_name = "Coffee";
        Role new_cat = entityManager.persist(new Role(cat_name));
        assertTrue(new_cat.getId()==1);
    }

    @Test
    public void testSaveProduct2() {
        String cat_name = "Coffee";
        Role new_cat = entityManager.persist(new Role(cat_name));
        assertTrue(new_cat.getName() == cat_name);
    }

    @Test
    public void testUpdateProduct() {
        String cat_name = "Tea";
        Role new_cat = entityManager.persist(new Role(cat_name));
        new_cat.setName(cat_name);
        assertTrue(new_cat.getName()==cat_name);
    }
}
