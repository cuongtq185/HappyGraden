package vn.com.unit.repository;

import java.util.List;

import org.springframework.data.mirage.repository.MirageRepository;
import org.springframework.data.mirage.repository.query.Modifying;
import org.springframework.data.repository.query.Param;

import vn.com.unit.dto.ProductDto;
import vn.com.unit.entity.Product;

public interface ProductRepository extends MirageRepository<Product, Long> {

	public List<ProductDto> findAllProductByShopId(@Param("shop_id") Long shop_id,@Param("sizeOfPage") Integer sizeOfPage,@Param("offset") Integer offset);
	
	public List<Product> findAllProductByCategoryId(@Param("category_id") Long category_id,
			@Param("sizeOfPage") Integer sizeOfPage,
			@Param("offset") Integer offset
			);
	
	public List<Product> findAllProductByCategoryIdNotPagination(@Param("category_id") Long category_id
			);
	
	public List<Product> findAllProductByBrandId(@Param("brand_id") Long brand_id);

	public List<Product> findAllProductByCategoryIdAndBrandId(
			@Param("category_id") Long category_id,
			@Param("brand_id") Long brand_id,
			@Param("shop_id") Long shop_id,
			@Param("sizeOfPage") Integer sizeOfPage,
			@Param("offset") Integer offset);
	
	public ProductDto findProductByProductId(@Param("product_id") Long product_id); 
	
	@Modifying
	public Long createNewProduct(@Param("name") String name,								
								@Param("category") int category,
								@Param("origin") int origin,
								@Param("detail") String detail,
								@Param("img") String img
			);
	
	@Modifying
	public void setDisableProductByProductId(@Param("product_id") Long product_id, @Param("status") int status);
	
	@Modifying
	public void saveProduct(
			@Param("product_id") Long product_id,
			@Param("name") String name,
			@Param("price") int price,
			@Param("quantity") int quantity,
			@Param("category") int category,
			@Param("brand") int brand,
			@Param("detail") String detail
);
	
	public List<Product> findAllActiveProductOfShopActiveOfVendorActive();
	
	public List<Product> findProductByName(@Param("name") String name);
	
	public int countAllProductByShopId(@Param("shop_id") Long shop_id);
	
	public int countAllProductByCategoryIdAndBrandId(
			@Param("category_id") Long category_id,
			@Param("brand_id") Long brand_id,
			@Param("shop_id") Long shop_id
			);
	
	public int countAllProductByCategoryId(
			@Param("category_id") Long category_id
			);

	/*
	select top 1 *
	from p2p_product
	where id in
		(
		select top 10 bill_item.product
		from p2p_bill bill
		left join p2p_bill_separate bill_separate
		on bill_separate.bill = bill.id
		left join p2p_bill_item bill_item
		on bill_item.id = bill_separate.id
		left join p2p_product product
		on product.id = bill_item.product
		where bill.payment > 0
		group by bill_item.product
		order by count(bill_item.product) desc
		)
	order by newid();
	*/
	public Product findOneTopProductPaymentSuccess();
	
	public int countAllProductActive();
	
	public List<ProductDto> findAllProductActive(@Param("sizeOfPage") Integer sizeOfPage, @Param("offset") Integer offset);
	
}
