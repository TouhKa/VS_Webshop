package hska.iwi.eShopMaster.model.services;

import hska.iwi.eShopMaster.model.data.objects.Product;

/**
 * interface for the catalogue microservice
 */
public interface CatalogueService {
    
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
