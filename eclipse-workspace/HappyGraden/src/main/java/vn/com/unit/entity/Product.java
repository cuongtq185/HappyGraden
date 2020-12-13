package vn.com.unit.entity;

import java.util.Date;

import org.springframework.data.annotation.Id;

import jp.sf.amateras.mirage.annotation.Column;
import jp.sf.amateras.mirage.annotation.PrimaryKey;
import jp.sf.amateras.mirage.annotation.Table;
import jp.sf.amateras.mirage.annotation.PrimaryKey.GenerationType;

@Table(name = "product")
public class Product {
	
	@Id
	@PrimaryKey(generationType = GenerationType.IDENTITY) // Primary key // Auto increment
	@Column(name = "product_id")
	private Long productId;

	@Column(name = "product_name")
	private String productName;
	
	@Column(name = "product_detail")
	private String productDetail;
	
	@Column(name = "product_disable")
	private boolean productDisable;
	
	@Column(name = "product_createAt")
	private Date productCreateAt;	
	
	@Column(name = "category")
	private int category;
	
	@Column(name = "origin")
	private int origin;	
	
	public Product() {
	}
	
	public Product(Product product) {
		this.productId = product.getProductId();
		this.productName = product.getProductName();
		this.productDetail = product.getProductDetail();
		this.productDisable = product.isProductDisable();
		this.productCreateAt = product.getProductCreateAt();
		this.category = product.getCategory();
		this.origin = product.getOrigin();
		
	}

	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getProductDetail() {
		return productDetail;
	}

	public void setProductDetail(String productDetail) {
		this.productDetail = productDetail;
	}

	public boolean isProductDisable() {
		return productDisable;
	}

	public void setProductDisable(boolean productDisable) {
		this.productDisable = productDisable;
	}

	public Date getProductCreateAt() {
		return productCreateAt;
	}

	public void setProductCreateAt(Date productCreateAt) {
		this.productCreateAt = productCreateAt;
	}

	public int getCategory() {
		return category;
	}

	public void setCategory(int category) {
		this.category = category;
	}

	public int getOrigin() {
		return origin;
	}

	public void setOrigin(int origin) {
		this.origin = origin;
	}	
	
}