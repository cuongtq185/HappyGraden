package vn.com.unit.service;

import java.util.List;

import vn.com.unit.dto.ProductDto;
import vn.com.unit.entity.Product;

public interface ProductService {
	
	public List<ProductDto> findAllProductByShopId(Long shop_id,int limit,int offset);
	
	public List<Product> findAllProductByCategoryId(Long category_id,int limit,int offset);
	
	public List<Product> findAllProductByCategoryIdNotPagination(Long category_id);
	
	public List<Product> findAllProductByBrandId(Long brand_id);
	
	public List<Product> findAllProductByCategoryIdAndBrandId(Long category_id, Long brand_id,Long shop_id,int limit,int offset);
	
	public Product findProductByProductId(Long product_id);
	
//	public Product createNewProduct(String name,int price,int quantity,int category,int brand,String detail,String img,Long shop);
	public Product createNewProduct(Product product);
	public boolean setDisableProductByProductId(Long product_id, int status);
	
	public void saveProduct(Long product_id, String name, int price, String detail, int category, int brand, int quantity);
	
	public List<Product> findAllActiveProductOfShopActiveOfVendorActive();
	
	public List<Product> searchProductByName(String name);
	
	public int CountAllProductByShopId(Long shop_id);
	
	public int countAllProductByCategoryIdAndBrandId(Long category_id, Long brand_id,Long shop_id);
	
	public int countAllProductByCategoryId(Long category_id);
	
	public Product findOneTopProductPaymentSuccess();
	
	public Product save(Product product);
	
	public Product findOne(Long id);
}
