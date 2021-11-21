package com.vslab.webshop.model.businessLogic.manager;

import com.vslab.webshop.model.data.objects.Category;

import java.util.List;

public interface CategoryManager {

	List<Category> getCategories();
	
	Category getCategory(int id);
	
	void addCategory(String name);
	
	void delCategoryById(int id);

	
}
