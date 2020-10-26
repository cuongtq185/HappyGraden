package vn.com.unit.entity;

import org.springframework.data.annotation.Id;

import jp.sf.amateras.mirage.annotation.Column;
import jp.sf.amateras.mirage.annotation.PrimaryKey;
import jp.sf.amateras.mirage.annotation.PrimaryKey.GenerationType;
import jp.sf.amateras.mirage.annotation.Table;

@Table(name = "p2p_bill_separate")
public class BillSeparate {

	@Id
	@PrimaryKey(generationType = GenerationType.IDENTITY) // Primary key // Auto increment
	@Column(name = "id")
	private Long id;

	@Column(name = "bill")
	private Long bill;

	@Column(name = "shop")
	private Long shop;

	@Column(name = "status")
	private int status;

	@Column(name = "refund")
	private Long refund;
	
	public BillSeparate() {
	}

	public BillSeparate(BillSeparate billSeparate) {
		this.id = billSeparate.getId();
		this.bill = billSeparate.getBill();
		this.shop = billSeparate.getShop();
		this.status = billSeparate.getStatus();
		this.refund = billSeparate.getRefund();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getBill() {
		return bill;
	}

	public void setBill(Long bill) {
		this.bill = bill;
	}

	public Long getShop() {
		return shop;
	}

	public void setShop(Long shop) {
		this.shop = shop;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public Long getRefund() {
		return refund;
	}

	public void setRefund(Long refund) {
		this.refund = refund;
	}

}
