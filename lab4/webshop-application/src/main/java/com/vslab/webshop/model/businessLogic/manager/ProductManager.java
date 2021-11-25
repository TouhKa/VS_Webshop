package com.vslab.webshop.model.businessLogic.manager;

import com.vslab.webshop.model.data.objects.Product;

import java.util.List;

public interface ProductManager {

	List<Product> getProducts();

	Product getProductById(int id);

	int addProduct(String name, double price, int categoryId, String details);

	List<Product> getProductsForSearchValues(String searchDescription, Double searchMinPrice, Double searchMaxPrice);
	
	void deleteProduct(int id);
    
	
}
