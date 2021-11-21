package com.vslab.webshop.model.businessLogic.manager.impl;


import com.vslab.webshop.model.data.objects.Category;
import com.vslab.webshop.model.services.CatalogueService;
import com.vslab.webshop.model.services.CategoryService;
import com.vslab.webshop.model.services.impl.CatalogueServiceImpl;
import com.vslab.webshop.model.services.impl.CategoryServiceImpl;
import com.vslab.webshop.model.businessLogic.manager.CategoryManager;

import java.util.Arrays;
import java.util.List;

public class CategoryManagerImpl implements CategoryManager{

	private final CategoryService categoryService;
	
	private final CatalogueService catalogueService;
	
	public CategoryManagerImpl() {
		categoryService = new CategoryServiceImpl();
		catalogueService = new CatalogueServiceImpl();
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
		catalogueService.deleteCategory(id);
	}
}
