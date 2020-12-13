package vn.com.unit.entity;

import org.springframework.data.annotation.Id;

import jp.sf.amateras.mirage.annotation.Column;
import jp.sf.amateras.mirage.annotation.PrimaryKey;
import jp.sf.amateras.mirage.annotation.Table;
import jp.sf.amateras.mirage.annotation.PrimaryKey.GenerationType;

@Table(name = "product_img3D")
public class ProductImg3D {
	@Id
	@PrimaryKey(generationType = GenerationType.IDENTITY) // Primary key // Auto increment
	@Column(name = "id_product_img3D")
	private Long idProductImg3D;

	@Column(name = "product_img")
	private String productImg;
	

	public ProductImg3D() {
	}


	public Long getIdProductImg3D() {
		return idProductImg3D;
	}


	public void setIdProductImg3D(Long idProductImg3D) {
		this.idProductImg3D = idProductImg3D;
	}


	public String getProductImg() {
		return productImg;
	}


	public void setProductImg(String productImg) {
		this.productImg = productImg;
	}
	
	
}
