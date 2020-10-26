package vn.com.unit.service;

import java.util.List;

import vn.com.unit.entity.Brand;

public interface BrandService {
	
	public Brand findBrandByProductId(Long product_id);

	public List<Brand> findAllBrand();
	
	public List<Brand> findAllBrandByShopId(Long shop_id);
	
	public List<Brand> findBrandPageable(int limit,int offset);
	
	public int countAllBrand();
	
	//public Long createCategory(Brand brand);
	
	public Brand findBrandByName(String name);
	
	public Brand findBrandById(Long id);
	
	//public void updateBrandById(Brand brand);
	
	public void deleteBrandById(Long id,Long disable);
	
	public void createBrand(Brand brand);
	
	public void editBrand(Brand brand);

}
