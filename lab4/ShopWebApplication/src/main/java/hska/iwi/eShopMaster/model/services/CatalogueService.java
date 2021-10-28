package hska.iwi.eShopMaster.model.services;

/**
 * interface for the catalogue microservice
 */
public interface CatalogueService {
    
    /**
     * deletes a category and all products that have this category
     */
    void deleteCategory(int id);
    
}
