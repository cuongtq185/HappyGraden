package vn.com.unit.entity;

import org.springframework.data.annotation.Id;

import jp.sf.amateras.mirage.annotation.Column;
import jp.sf.amateras.mirage.annotation.PrimaryKey;
import jp.sf.amateras.mirage.annotation.PrimaryKey.GenerationType;
import jp.sf.amateras.mirage.annotation.Table;

@Table(name = "category")
public class Category {

	@Id
	@PrimaryKey(generationType = GenerationType.IDENTITY) // Primary key // Auto increment
	@Column(name = "category_id")
	private Long categoryId;

	@Column(name = "category_name")
	private String categoryName;
	

	public Category() {
	}

	public Long getCategoryId() {
		return categoryId;
	}



	public void setCategoryId(Long categoryId) {
		this.categoryId = categoryId;
	}



	public String getCategoryName() {
		return categoryName;
	}



	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	

}
