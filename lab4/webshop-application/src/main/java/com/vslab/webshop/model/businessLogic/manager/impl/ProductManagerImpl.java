package com.vslab.webshop.model.businessLogic.manager.impl;

import com.vslab.webshop.model.data.objects.Category;
import com.vslab.webshop.model.data.objects.Product;
import com.vslab.webshop.model.services.CatalogueConnector;
import com.vslab.webshop.model.services.ProductConnector;
import com.vslab.webshop.model.services.impl.CatalogueConnectorImpl;
import com.vslab.webshop.model.services.impl.ProductConnectorImpl;
import com.vslab.webshop.model.businessLogic.manager.CategoryManager;
import com.vslab.webshop.model.businessLogic.manager.ProductManager;
import com.vslab.webshop.model.util.DockerLogger;
import java.util.List;
import java.util.Optional;

public class ProductManagerImpl implements ProductManager {
	private DockerLogger logger = new DockerLogger(ProductManagerImpl.class.getSimpleName());
	private final ProductConnector productConnector;
	
	private final CatalogueConnector catalogueConnector;

	public ProductManagerImpl() {

		productConnector = new ProductConnectorImpl();
		catalogueConnector = new CatalogueConnectorImpl();
	}

	public List<Product> getProducts() {
		return productConnector.getAllProducts();
	}
	
	public List<Product> getProductsForSearchValues(String searchDescription,
		Double searchMinPrice, Double searchMaxPrice) {
		Optional<String> sD = searchDescription.equals("")? null : Optional.ofNullable(searchDescription);
		Optional<String> minP = searchMinPrice== null ? null : Optional.ofNullable(String.valueOf(searchMinPrice));
		Optional<String> maxP = searchMaxPrice== null ? null : Optional.ofNullable(String.valueOf(searchMaxPrice));

		return productConnector.getAllProducts(sD, minP, maxP);
	}

	public Product getProductById(int id) {
		return productConnector.getProduct(id);
	}
	
	public int addProduct(String name, double price, int categoryId, String details) {
		int productId = -1;
		CategoryManager categoryManager = new CategoryManagerImpl();
		Category category = categoryManager.getCategory(categoryId);
		logger.write("Category name " + category.getName());
		logger.close();
		if(!category.getName().equals("fallback")){
			Product product;
			if (details == null) {
				product = new Product(name, price, category.getId(), "");
			}
			else {
				product = new Product(name, price, category.getId(), details);
			}
			logger.write("Product name " + product.getName());
			logger.close();

			Product addedProduct = catalogueConnector.addProduct(product);
			logger.write("Added Product name " + addedProduct.getName());
			logger.close();
			if (addedProduct != null) {
				// a product was successfully added
				productId = addedProduct.getId();
				
			}

		}
			 
		return productId;
	}
	
	@Override
	public void deleteProduct(int id) {
		productConnector.deleteProduct(id);
	}
}
