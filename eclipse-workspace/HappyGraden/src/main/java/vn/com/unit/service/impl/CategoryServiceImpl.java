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
//import vn.com.unit.repository.categoryEntityRepository;
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

			Category category_new = new Category();
			category_new.setName(category.getName());
			category_new.setDisable(false);
			return categoryRepository.save(category_new);
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
//			Category category_temp = new Category();
//
//			category_temp.setId(category.getId());
			
			Category category_temp = categoryRepository.findOne(category.getId());
			
			category_temp.setName(category.getName());

//			categoryRepository.updateCategoryById(category.getId(), category.getName());
			categoryRepository.save(category_temp);

		} catch (Exception e) {
			// TODO: handle exception
		}
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteCategoryById(Long id, Long disable) {
		// TODO Auto-generated method stub
		try {
			categoryRepository.deleteCategoryById(id, disable);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

//	@Override
//	public categoryEntity createNewCategory(categoryEntity category) {
//		return categoryEntityRepository.save(category);
//	}

}
