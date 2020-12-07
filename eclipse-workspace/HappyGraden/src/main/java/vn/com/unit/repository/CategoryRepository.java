package vn.com.unit.repository;

import java.util.List;

import org.springframework.data.mirage.repository.MirageRepository;
import org.springframework.data.repository.query.Param;

import vn.com.unit.entity.Category;

public interface CategoryRepository extends MirageRepository<Category, Long> {

	public Category findCategoryByProductId(@Param("product_id") Long product_id);
	
	public List<Category> findAllCategory();
	
	public List<Category> findAllCategoryByShopId(@Param("shop_id") Long shop_id );
	
	public List<Category> findCategoryPageable(@Param("sizeOfPage") Integer sizeOfPage,@Param("offset") Integer offset);
	
	public int countAllCategory();
	
	public Category createCategory(@Param("name") String name);
	
	public Category findCategoryByName(@Param("name") String name);
	
	public Category findCategoryById(@Param("id") Long id);
	
	public void updateCategoryById(@Param("id") Long id,@Param("name") String name);
	
	public void deleteCategoryById(@Param("id") Long id);
}
