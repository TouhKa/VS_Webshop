package com.vslab.webshop.web;

import com.vslab.webshop.Category;
import com.vslab.webshop.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping("/category/")
    public Category[] getAllCategories() {
        return categoryService.getAll();
    }

    @PostMapping(value = "/category/", consumes = "application/json")
    public Category addCategory(@RequestBody Category category){
        return categoryService.addCategory(category);
    }

    @GetMapping("/category/{id}")
    public Category getCategory(@PathVariable int id){
        return categoryService.getCategoryById(id);
    }

    @PutMapping(value = "/category/", consumes = "application/json")
    public Category updateCategory(@RequestBody Category category){
        return categoryService.updateCategorie(category);
    }
    @DeleteMapping("/category/{id}")
    public void deleteCategory(@PathVariable int id){
        categoryService.deleteCategory(id);
    }
}
