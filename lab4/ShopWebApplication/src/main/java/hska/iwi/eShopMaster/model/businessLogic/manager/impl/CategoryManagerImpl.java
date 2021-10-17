package hska.iwi.eShopMaster.model.businessLogic.manager.impl;


import hska.iwi.eShopMaster.controller.microservices.CategoryServiceAction;
import hska.iwi.eShopMaster.model.businessLogic.manager.CategoryManager;
import hska.iwi.eShopMaster.model.businessLogic.manager.impl.microservices.Category;

import java.util.Arrays;
import java.util.List;

public class CategoryManagerImpl implements CategoryManager{

	private CategoryServiceAction categoryServiceAction;
	public CategoryManagerImpl() {
		categoryServiceAction = new CategoryServiceAction();
	}

	public List<Category> getCategories() {
		Category[] categories = categoryServiceAction.getAll();
		return Arrays.asList(categories);
	}

	public Category getCategory(int id) {
		return categoryServiceAction.getCategoryById(id);
	}

	public Category getCategoryByName(String name) {
		return categoryServiceAction.getCategoryByName(name);
	}

	public void addCategory(String name) {
		Category cat = new Category(name);
		categoryServiceAction.addCategory(cat);
	}

	public void delCategory(Category cat) {
	//TODO Daniel
	// must be replaced with corresponding action of catalogue

//		helper.deleteById(cat.getId());
	}

	public void delCategoryById(int id) {
		//TODO Daniel
		// must be replaced with corresponding action of catalogue
//		helper.deleteById(id);
	}
}
