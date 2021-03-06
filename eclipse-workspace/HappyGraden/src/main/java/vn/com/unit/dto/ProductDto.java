package vn.com.unit.dto;

import vn.com.unit.entity.Product;

public class ProductDto extends Product {

	private String originName;
	
	private int originId;
	
	private int categoryId; 
	
	private String categoryName;
	
	private int productPrice;
	
	private String productImg;
	
	public int getProductPrice() {
		return productPrice;
	}


	public void setProductPrice(int productPrice) {
		this.productPrice = productPrice;
	}


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


	public String getProductImg() {
		return productImg;
	}

	public void setProductImg(String productImg) {
		this.productImg = productImg;
	}


	public int getOriginId() {
		return originId;
	}


	public void setOriginId(int originId) {
		this.originId = originId;
	}


	public int getCategoryId() {
		return categoryId;
	}


	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}
	
	

//	public Product extractProduct() {
//		return (Product) this;
//	}
	
}
