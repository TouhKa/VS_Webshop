package com.vslab.categories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CategoryService {
    @Autowired
    private CategoryRepo categoryRepo;

    public CategoryService() {}

    public Category[] getAll() {
        List<Category> products = categoryRepo.findAll();
        Category[] allCategories = new Category[products.size()];
        return products.toArray(allCategories);
    }

    public Category addCategory(Category category) {

        return categoryRepo.save(category);
    }

    public Category getCategoryById(int id) {
         return categoryRepo.findById(id);
    }

    public Category updateCategorie(Category category) {
        return categoryRepo.save(category);
    }

    public void deleteCategory(int id) {
         categoryRepo.deleteById(id);
    }
}
