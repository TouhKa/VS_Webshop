package com.vslab.webshop.model.services;

import com.vslab.webshop.model.data.objects.Category;

/**
 * interface for the category microservice
 */
public interface CategoryService {
    
    Category[] getAll();
    
    Category getCategoryById(int id);
    
    void addCategory(Category category);
    
    void deleteCategory(int id);
    
}
