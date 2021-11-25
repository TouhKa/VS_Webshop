package com.vslab.webshop.model.businessLogic.manager.impl;

import com.vslab.webshop.model.data.objects.Category;
import com.vslab.webshop.model.data.objects.Product;
import com.vslab.webshop.model.services.CatalogueConnector;
import com.vslab.webshop.model.services.ProductService;
import com.vslab.webshop.model.services.impl.CatalogueConnectorImpl;
import com.vslab.webshop.model.services.impl.ProductServiceImpl;
import com.vslab.webshop.model.businessLogic.manager.CategoryManager;
import com.vslab.webshop.model.businessLogic.manager.ProductManager;

import java.util.List;
import java.util.Optional;

public class ProductManagerImpl implements ProductManager {

	private final ProductService productService;
	
	private final CatalogueConnector catalogueConnector;

	public ProductManagerImpl() {

		productService = new ProductServiceImpl();
		catalogueConnector = new CatalogueConnectorImpl();
	}

	public List<Product> getProducts() {
		return productService.getAllProducts();
	}
	
	public List<Product> getProductsForSearchValues(String searchDescription,
		Double searchMinPrice, Double searchMaxPrice) {
		Optional<String> sD = searchDescription.equals("")? null : Optional.ofNullable(searchDescription);
		Optional<String> minP = searchMinPrice== null ? null : Optional.ofNullable(String.valueOf(searchMinPrice));
		Optional<String> maxP = searchMaxPrice== null ? null : Optional.ofNullable(String.valueOf(searchMaxPrice));

		return productService.getAllProducts(sD, minP, maxP);
	}

	public Product getProductById(int id) {
		return productService.getProduct(id);
	}
	
	public int addProduct(String name, double price, int categoryId, String details) {
		int productId = -1;
		
		CategoryManager categoryManager = new CategoryManagerImpl();
		Category category = categoryManager.getCategory(categoryId);
		
		if(!category.getName().equals("fallback")){
			Product product;
			if (details == null) {
				product = new Product(name, price, category.getId(), "");
			}
			else {
				product = new Product(name, price, category.getId(), details);
			}

			Product addedProduct = catalogueConnector.addProduct(product);
			if (addedProduct != null) {
				// a product was successfully added
				productId = addedProduct.getId();
				
			}

		}
			 
		return productId;
	}
	
	@Override
	public void deleteProduct(int id) {
	
	}
}
