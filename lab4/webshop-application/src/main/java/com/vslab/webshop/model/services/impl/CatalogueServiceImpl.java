package com.vslab.webshop.model.services.impl;

import com.vslab.webshop.model.data.objects.Category;
import com.vslab.webshop.model.data.objects.Product;
import com.vslab.webshop.model.services.CatalogueService;
import com.vslab.webshop.model.services.CategoryService;
import com.vslab.webshop.model.services.ProductService;

public class CatalogueServiceImpl implements CatalogueService {
    
    private final ProductService productService;
    
    private final CategoryService categoryService;
    
    public CatalogueServiceImpl() {
        this.productService = new ProductServiceImpl();
        this.categoryService = new CategoryServiceImpl();
    }
    
    @Override
    public Product addProduct(Product product) {
        for (Category category : categoryService.getAll()) {
            if (product.getCategoryId() == category.getId()) {
                productService.addProduct(product);
                return product;
            }
        }
        // in case no category had the same ID we return null
        return null;
    }
    
    @Override
    public void deleteCategory(int id) {
        // first delete all products that have this category id
        for (Product product : productService.getAllProducts()) {
            if (product.getCategoryId() == id) {
                productService.deleteProduct(id);
            }
        }
        // then we delete the category
        categoryService.deleteCategory(id);
    }
}
