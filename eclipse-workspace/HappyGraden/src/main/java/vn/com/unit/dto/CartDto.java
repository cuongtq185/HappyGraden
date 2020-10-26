package vn.com.unit.dto;

import vn.com.unit.entity.Cart;

public class CartDto extends Cart {

	private String img;

	private String name;

	private Long price;

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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
	
	public Cart extractCart() {
		return (Cart) this;
	}
	
}
