package vn.com.unit.entity;

import java.util.Date;

import jp.sf.amateras.mirage.annotation.Column;
import jp.sf.amateras.mirage.annotation.Table;

@Table(name = "p2p_cart")
public class Cart {
	
	@Column(name = "account")
	private Long account;
	
	@Column(name = "product")
	private Long product;
	
	@Column(name = "quantity")
	private int quantity;
	
	@Column(name = "create_at")
	private Date createAt;

	public Cart() {
	}

	public Long getAccount() {
		return account;
	}

	public void setAccount(Long account) {
		this.account = account;
	}

	public Long getProduct() {
		return product;
	}

	public void setProduct(Long product) {
		this.product = product;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public Date getCreateAt() {
		return createAt;
	}

	public void setCreateAt(Date createAt) {
		this.createAt = createAt;
	}
	
}
