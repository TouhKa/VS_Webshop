package com.vslab.webshop.controller;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.vslab.webshop.model.data.objects.Category;
import com.vslab.webshop.model.data.objects.Product;
import com.vslab.webshop.model.data.objects.User;
import com.vslab.webshop.model.businessLogic.manager.CategoryManager;
import com.vslab.webshop.model.businessLogic.manager.ProductManager;
import com.vslab.webshop.model.businessLogic.manager.impl.CategoryManagerImpl;
import com.vslab.webshop.model.businessLogic.manager.impl.ProductManagerImpl;

import java.util.Map;

public class ProductDetailsAction extends ActionSupport {
	
	private User user;
	private int id;
	private String searchValue;
	private Integer searchMinPrice;
	private Integer searchMaxPrice;
	private Product product;
	private Category category;

	/**
	 * 
	 */
	private static final long serialVersionUID = 7708747680872125699L;

	public String execute() throws Exception {

		String res = "input";
		
		Map<String, Object> session = ActionContext.getContext().getSession();
		user = (User) session.get("webshop_user");
		if(user != null) {
			ProductManager productManager = new ProductManagerImpl();
			product = productManager.getProductById(id);
			CategoryManager categoryManager = new CategoryManagerImpl();
			category = categoryManager.getCategory(product.getCategoryId());
			res = "success";			
		}
		
		return res;		
	}
	
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getSearchValue() {
		return searchValue;
	}

	public void setSearchValue(String searchValue) {
		this.searchValue = searchValue;
	}

	public Integer getSearchMinPrice() {
		return searchMinPrice;
	}

	public void setSearchMinPrice(Integer searchMinPrice) {
		this.searchMinPrice = searchMinPrice;
	}

	public Integer getSearchMaxPrice() {
		return searchMaxPrice;
	}

	public void setSearchMaxPrice(Integer searchMaxPrice) {
		this.searchMaxPrice = searchMaxPrice;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public Category getCategory() { return category; }
}
