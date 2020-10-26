package vn.com.unit.dto;

import vn.com.unit.entity.Product;

public class ProductDto extends Product {

	private String brandName;
	
	private String categoryName;

	public ProductDto(Product product) {
		super(product);
	}
	
	public ProductDto() {
		// TODO Auto-generated constructor stub
	}

	public String getBrandName() {
		return brandName;
	}

	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}
	
	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public Product extractProduct() {
		return (Product) this;
	}
	
}
