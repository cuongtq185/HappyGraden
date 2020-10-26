package vn.com.unit.entity;

import java.util.Date;

import org.springframework.data.annotation.Id;

import jp.sf.amateras.mirage.annotation.Column;
import jp.sf.amateras.mirage.annotation.PrimaryKey;
import jp.sf.amateras.mirage.annotation.Table;
import jp.sf.amateras.mirage.annotation.PrimaryKey.GenerationType;

@Table(name = "p2p_product")
public class Product {
	
	@Id
	@PrimaryKey(generationType = GenerationType.IDENTITY) // Primary key // Auto increment
	@Column(name = "id")
	private Long id;

	@Column(name = "shop")
	private Long shop;
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "price")
	private int price;
	
	@Column(name = "detail")
	private String detail;
	
	@Column(name = "img")
	private String img;
	
	@Column(name = "disable")
	private boolean disable;
	
	@Column(name = "category")
	private int category;
	
	@Column(name = "brand")
	private int brand;
	
	@Column(name = "quantity")
	private int quantity;

	@Column(name = "create_at")
	private Date createAt;
	
	public Product() {
	}
	
	public Product(Product product) {
		this.id = product.getId();
		this.shop = product.getShop();
		this.name = product.getName();
		this.price = product.getPrice();
		this.detail = product.getDetail();
		this.img = product.getImg();
		this.disable = product.isDisable();
		this.category = product.getCategory();
		this.brand = product.getBrand();
		this.quantity = product.getQuantity();
		this.createAt = product.getCreateAt();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getShop() {
		return shop;
	}

	public void setShop(Long shop) {
		this.shop = shop;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}

	public int getCategory() {
		return category;
	}

	public void setCategory(int category) {
		this.category = category;
	}

	public boolean isDisable() {
		return disable;
	}

	public void setDisable(boolean disable) {
		this.disable = disable;
	}

	public Date getCreateAt() {
		return createAt;
	}

	public void setCreateAt(Date createAt) {
		this.createAt = createAt;
	}

	public int getBrand() {
		return brand;
	}

	public void setBrand(int brand) {
		this.brand = brand;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
}