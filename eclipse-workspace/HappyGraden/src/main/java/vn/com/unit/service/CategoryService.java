package vn.com.unit.service;

import java.util.List;

import vn.com.unit.entity.Category;
//import vn.com.unit.entity.categoryEntity;

public interface CategoryService {
	
	public Category findCategoryByProductId(Long product_id);

	public List<Category> findAllCategory();
	
	public List<Category> findAllCategoryByShopId(Long shop_id);
	
	public List<Category> findCategoryPageable(int limit,int offset);
	
	public int countAllCategory();
		
	public Category createCategory(Category category);
//	public categoryEntity createNewCategory(categoryEntity category);

	
	public Category findCategoryByName(String Name);
	
	public Category findCategoryById(Long id);
	
	public void updateCategoryById(Category category);
	
	public void deleteCategoryById(Long id);
}
