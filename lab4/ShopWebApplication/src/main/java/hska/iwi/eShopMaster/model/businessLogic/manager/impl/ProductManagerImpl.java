package hska.iwi.eShopMaster.model.businessLogic.manager.impl;

import hska.iwi.eShopMaster.model.services.CatalogueService;
import hska.iwi.eShopMaster.model.services.CategoryService;
import hska.iwi.eShopMaster.model.services.ProductService;
import hska.iwi.eShopMaster.model.services.impl.CatalogueServiceImpl;
import hska.iwi.eShopMaster.model.services.impl.ProductServiceImpl;
import hska.iwi.eShopMaster.model.businessLogic.manager.CategoryManager;
import hska.iwi.eShopMaster.model.businessLogic.manager.ProductManager;
import hska.iwi.eShopMaster.model.data.objects.Category;
import hska.iwi.eShopMaster.model.data.objects.Product;

import java.util.List;
import java.util.Optional;

public class ProductManagerImpl implements ProductManager {

	private final ProductService productService;
	
	private final CatalogueService catalogueService;

	public ProductManagerImpl() {

		productService = new ProductServiceImpl();
		catalogueService = new CatalogueServiceImpl();
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

			// TODO unsicher wieso hier catalogue aufgerufen werden muss?
			productService.addProduct(product);
			productId = product.getId();
		}
			 
		return productId;
	}

}
