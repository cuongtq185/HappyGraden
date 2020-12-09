package vn.com.unit.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import vn.com.unit.entity.Category;
//import vn.com.unit.entity.categoryEntity;
import vn.com.unit.repository.BrandRepository;
import vn.com.unit.repository.CategoryRepository;
import vn.com.unit.repository.ProductRepository;
import vn.com.unit.repository.ShopRepository;
import vn.com.unit.service.CategoryService;

@Service
@Transactional
public class CategoryServiceImpl implements CategoryService {

	@Autowired
	ShopRepository shopRepository;

	@Autowired
	ProductRepository productRepository;

	@Autowired
	BrandRepository brandRepository;

	@Autowired
	CategoryRepository categoryRepository;

//	@Autowired
//	private categoryEntityRepository categoryEntityRepository;

	@Override
	public Category findCategoryByProductId(Long product_id) {

		return categoryRepository.findCategoryByProductId(product_id);

	};

	@Override
	public List<Category> findAllCategory() {
		return categoryRepository.findAllCategory();
	}

	@Override
	public List<Category> findAllCategoryByShopId(Long shop_id) {
		return categoryRepository.findAllCategoryByShopId(shop_id);
	}

	@Override
	public List<Category> findCategoryPageable(int limit, int offset) {
		// TODO Auto-generated method stub
		try {
			return categoryRepository.findCategoryPageable(limit, offset);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return null;
	}

	@Override
	public int countAllCategory() {
		// TODO Auto-generated method stub
		try {
			return categoryRepository.countAllCategory();
		} catch (Exception e) {
			// TODO: handle exception
		}
		return 0;
	}

	@Override
	public Category createCategory(Category category) {
		// TODO Auto-generated method stub
		try {
			return categoryRepository.createCategory(category.getCategoryName());
		} catch (Exception e) {
			// TODO: handle exception
		}
		return null;
	}

	@Override
	public Category findCategoryByName(String Name) {
		// TODO Auto-generated method stub
		try {
			return categoryRepository.findCategoryByName(Name);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return null;
	}

	@Override
	public Category findCategoryById(Long id) {
		// TODO Auto-generated method stub
		try {
			return categoryRepository.findCategoryById(id);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return null;
	}

	@Override
	public void updateCategoryById(Category category) {
		try {
			Category category_temp = new Category();

			category_temp.setCategoryId(category.getCategoryId());
			
			/*
			 * Category category_temp =
			 * categoryRepository.findOne(category.getCategoryId());
			 * 
			 * category_temp.setCategoryName(category.getCategoryName());
			 */

			categoryRepository.updateCategoryById(category.getCategoryId(), category.getCategoryName());
//			categoryRepository.save(category_temp);

		} catch (Exception e) {
			// TODO: handle exception
		}
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteCategoryById(Long id) {
		// TODO Auto-generated method stub
		try {
			categoryRepository.deleteCategoryById(id);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

//	@Override
//	public categoryEntity createNewCategory(categoryEntity category) {
//		return categoryEntityRepository.save(category);
//	}

}
