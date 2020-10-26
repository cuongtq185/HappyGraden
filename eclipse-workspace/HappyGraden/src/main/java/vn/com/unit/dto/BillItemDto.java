package vn.com.unit.dto;

import vn.com.unit.entity.BillItem;

public class BillItemDto extends BillItem {

	private String productName;

	private String brandName;
	 
	private String categoryName;

	private String img;

	private Long price;

	public BillItemDto() {
	}

	public BillItemDto(BillItem billItem) {
		super(billItem);
	}

	public String getProductName() {
		return productName;
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

	public void setProductName(String productName) {
		this.productName = productName;
	}
	
	

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}

	public Long getPrice() {
		return price;
	}

	public void setPrice(Long price) {
		this.price = price;
	}

	public Long getTotal() {
		return this.getPrice() * this.getQuantity();
	}
	
	public BillItem extractBillItem( ) {
		return (BillItem) this;
	}
	
}
