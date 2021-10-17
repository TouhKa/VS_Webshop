package hska.iwi.eShopMaster.model.businessLogic.manager.impl;

import hska.iwi.eShopMaster.controller.microservices.CategoryServiceAction;
import hska.iwi.eShopMaster.controller.microservices.ProductServiceAction;
import hska.iwi.eShopMaster.model.businessLogic.manager.CategoryManager;
import hska.iwi.eShopMaster.model.businessLogic.manager.ProductManager;
import hska.iwi.eShopMaster.model.businessLogic.manager.impl.microservices.Category;
import hska.iwi.eShopMaster.model.businessLogic.manager.impl.microservices.Product;
import hska.iwi.eShopMaster.model.util.DockerLogger;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class ProductManagerImpl implements ProductManager {
//	private ProductDAO helper;

	private CategoryServiceAction categoryServiceAction;
	private ProductServiceAction productServiceAction;
	private DockerLogger logger;

	public ProductManagerImpl() {
		logger = new DockerLogger(ProductManagerImpl.class.getSimpleName());
		categoryServiceAction = new CategoryServiceAction();
		productServiceAction = new ProductServiceAction();
	}

	public List<Product> getProducts() {
		Product[] products =  productServiceAction.getAllProducts();
		return Arrays.asList(products);
	}
	
	public List<Product> getProductsForSearchValues(String searchDescription,
		Double searchMinPrice, Double searchMaxPrice) {
		Optional<String> sD = searchDescription.equals("")? null : Optional.ofNullable(searchDescription);
		Optional<String> minP = searchMinPrice== null ? null : Optional.ofNullable(String.valueOf(searchMinPrice));
		Optional<String> maxP = searchMaxPrice== null ? null : Optional.ofNullable(String.valueOf(searchMaxPrice));

		logger.write("Product search params: " + sD + ", " + minP + ", " + maxP);
		logger.close();
		Product[] products =  productServiceAction.getAllProducts(sD, minP, maxP);
		return Arrays.asList(products);
	}

	public Product getProductById(int id) {

		return productServiceAction.getProduct(id);
	}

	public Product getProductByName(String name) {
		return productServiceAction.getProductByName(name);
	}
	
	public int addProduct(String name, double price, int categoryId, String details) {
		int productId = -1;
		
		CategoryManager categoryManager = new CategoryManagerImpl();
		Category category = categoryManager.getCategory(categoryId);
		
		if(!category.getName().equals("fallback")){
			Product product;
			if(details == null){
				product = new Product(name, price, category.getId(), "");
			} else{
				product = new Product(name, price, category.getId(), details);
			}
			
			productServiceAction.addProduct(product);
			productId = product.getId();
		}
			 
		return productId;
	}



	public void deleteProductById(int id) {
		productServiceAction.deleteProduct(id);
	}

	public boolean deleteProductsByCategoryId(int categoryId) {
		// TODO Auto-generated method stub
		return false;
	}

}
