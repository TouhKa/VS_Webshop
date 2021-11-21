package com.vslab.webshop.model.businessLogic.manager.impl;


import com.vslab.webshop.model.data.objects.Category;
import com.vslab.webshop.model.services.CatalogueConnector;
import com.vslab.webshop.model.services.CategoryService;
import com.vslab.webshop.model.services.impl.CatalogueConnectorImpl;
import com.vslab.webshop.model.services.impl.CategoryServiceImpl;
import com.vslab.webshop.model.businessLogic.manager.CategoryManager;

import java.util.Arrays;
import java.util.List;

public class CategoryManagerImpl implements CategoryManager{

	private final CategoryService categoryService;
	
	private final CatalogueConnector catalogueConnector;
	
	public CategoryManagerImpl() {
		categoryService = new CategoryServiceImpl();
		catalogueConnector = new CatalogueConnectorImpl();
	}

	public List<Category> getCategories() {
		Category[] categories = categoryService.getAll();
		return Arrays.asList(categories);
	}

	public Category getCategory(int id) {
		return categoryService.getCategoryById(id);
	}

	public void addCategory(String name) {
		Category cat = new Category(name);
		categoryService.addCategory(cat);
	}

	public void delCategoryById(int id) {
		catalogueConnector.deleteCategory(id);
	}
}
