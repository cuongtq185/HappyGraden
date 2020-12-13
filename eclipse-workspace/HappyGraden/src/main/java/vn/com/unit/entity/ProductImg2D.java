package vn.com.unit.entity;

import org.springframework.data.annotation.Id;

import jp.sf.amateras.mirage.annotation.Column;
import jp.sf.amateras.mirage.annotation.PrimaryKey;
import jp.sf.amateras.mirage.annotation.Table;
import jp.sf.amateras.mirage.annotation.PrimaryKey.GenerationType;

@Table(name = "product_img2D")
public class ProductImg2D {
	@Id
	@PrimaryKey(generationType = GenerationType.IDENTITY) // Primary key // Auto increment
	@Column(name = "id_product_img2D")
	private Long idProductImg2D;

	@Column(name = "product_img")
	private String productImg;
	

	public ProductImg2D() {
	}


	public Long getIdProductImg2D() {
		return idProductImg2D;
	}


	public void setIdProductImg2D(Long idProductImg2D) {
		this.idProductImg2D = idProductImg2D;
	}


	public String getProductImg() {
		return productImg;
	}


	public void setProductImg(String productImg) {
		this.productImg = productImg;
	}
	
	
}
