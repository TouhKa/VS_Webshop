package hska.iwi.eShopMaster.model.businessLogic.manager.impl;


import hska.iwi.eShopMaster.model.services.CatalogueService;
import hska.iwi.eShopMaster.model.services.CategoryService;
import hska.iwi.eShopMaster.model.services.impl.CatalogueServiceImpl;
import hska.iwi.eShopMaster.model.services.impl.CategoryServiceImpl;
import hska.iwi.eShopMaster.model.businessLogic.manager.CategoryManager;
import hska.iwi.eShopMaster.model.data.objects.Category;

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
