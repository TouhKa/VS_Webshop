package hska.iwi.eShopMaster.controller;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import hska.iwi.eShopMaster.model.businessLogic.manager.CategoryManager;
import hska.iwi.eShopMaster.model.businessLogic.manager.ProductManager;
import hska.iwi.eShopMaster.model.businessLogic.manager.impl.CategoryManagerImpl;
import hska.iwi.eShopMaster.model.businessLogic.manager.impl.ProductManagerImpl;
import hska.iwi.eShopMaster.model.businessLogic.manager.impl.microservices.Category;
import hska.iwi.eShopMaster.model.businessLogic.manager.impl.microservices.Product;
import hska.iwi.eShopMaster.model.businessLogic.manager.impl.microservices.User;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ListAllProductsAction extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = -94109228677381902L;
	
	User user;
	private List<Product> products;
	private List<Category> categories = new ArrayList<Category>();

	public String execute() throws Exception{
		String result = "input";
		
		Map<String, Object> session = ActionContext.getContext().getSession();
		user = (User) session.get("webshop_user");
		
		if(user != null){
			System.out.println("list all products!");
			ProductManager productManager = new ProductManagerImpl();
			this.products = productManager.getProducts();
			CategoryManager categoryManager = new CategoryManagerImpl();
			for (Product product : products) {
				categories.add(categoryManager.getCategory(product.getCategoryId()));
			}
			result = "success";
		}
		
		return result;
	}
	
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	public List<Product> getProducts() {
		return products;
	}

	public List<Category> getCategories() {
		return categories;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}

}
