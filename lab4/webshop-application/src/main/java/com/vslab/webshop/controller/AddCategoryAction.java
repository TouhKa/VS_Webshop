package com.vslab.webshop.controller;

import com.vslab.webshop.model.data.objects.Category;
import com.vslab.webshop.model.data.objects.User;
import com.vslab.webshop.model.businessLogic.manager.CategoryManager;
import com.vslab.webshop.model.businessLogic.manager.impl.CategoryManagerImpl;

import java.util.List;
import java.util.Map;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class AddCategoryAction extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6704600867133294378L;
	
	private String newCatName = null;
	
	private List<Category> categories;
	
	User user;

	public String execute() throws Exception {

		String res = "input";

		Map<String, Object> session = ActionContext.getContext().getSession();
		user = (User) session.get("webshop_user");
		if(user != null && (user.getRoleId()==2)) { //is admin
			CategoryManager categoryManager = new CategoryManagerImpl();
			//Add category
			categoryManager.addCategory(newCatName);

			// Go and get new Category list
			this.setCategories(categoryManager.getCategories());
			
			res = "success";
		}
		
		return res;
	
	}
	
	@Override
	public void validate(){
		if (getNewCatName().length() == 0) {
			addActionError(getText("error.catname.required"));
		}
		// Go and get new Category list
		CategoryManager categoryManager = new CategoryManagerImpl();
		this.setCategories(categoryManager.getCategories());
	}

	public List<Category> getCategories() {
		return categories;
	}

	public void setCategories(List<Category> categories) {
		this.categories = categories;
	}
	
	public String getNewCatName() {
		return newCatName;
	}

	public void setNewCatName(String newCatName) {
		this.newCatName = newCatName;
	}
}
