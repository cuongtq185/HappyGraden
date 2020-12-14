package vn.com.unit.dto;

import vn.com.unit.entity.Product;

public class ProductDto extends Product {

	private String originName;
	
	private String categoryName;
	
	
	public ProductDto() {
		// TODO Auto-generated constructor stub
	}


	public String getOriginName() {
		return originName;
	}


	public void setOriginName(String originName) {
		this.originName = originName;
	}


	public String getCategoryName() {
		return categoryName;
	}


	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	

//	public Product extractProduct() {
//		return (Product) this;
//	}
	
}
