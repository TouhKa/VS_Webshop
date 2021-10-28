package hska.iwi.eShopMaster.model.services;

import hska.iwi.eShopMaster.model.data.objects.Product;

import java.util.List;
import java.util.Optional;

/**
 * interface for the product microservice
 */
public interface ProductService {
    
    Product getProduct(Integer id);
    
    List<Product> getAllProducts();
    
    List<Product> getAllProducts(Optional<String> searchValue, Optional<String> minPrice, Optional<String> maxPrice);
    
    void addProduct(Product product);
    
    void deleteProduct(int id);
    
}
