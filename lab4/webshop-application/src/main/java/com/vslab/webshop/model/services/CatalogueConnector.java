package com.vslab.webshop.model.services;

import com.vslab.webshop.model.data.objects.Product;

/**
 * interface that defines a class that can connect to the Catalogue microservice and execute queries and actions on it.
 */
public interface CatalogueConnector {
    
    /**
     * deletes a category and all products that have this category
     */
    void deleteCategory(int id);
    
    /**
     * adds a product if the category of the product already exists. If the category does not exist, the product is not
     * added
     * @return the product that was added or NULL if no product was added
     */
    Product addProduct(Product product);
    
}
