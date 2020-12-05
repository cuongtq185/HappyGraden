package vn.com.unit.entity;

import java.beans.ConstructorProperties;
import java.util.Date;

import org.springframework.data.annotation.Id;

import jp.sf.amateras.mirage.annotation.Column;
import jp.sf.amateras.mirage.annotation.PrimaryKey;
import jp.sf.amateras.mirage.annotation.PrimaryKey.GenerationType;
import jp.sf.amateras.mirage.annotation.Table;

@Table(name = "account")
public class Account {

	@Id
	@PrimaryKey(generationType = GenerationType.IDENTITY) // Primary key // Auto increment
	@Column(name = "account_id")
	private Long accountId;

	@Column(name = "account_name")
	private String accountName;

	@Column(name = "account_username")
	private String accountUsername;

	@Column(name = "account_password")
	private String accountPassword;

	@Column(name = "account_email")
	private String accountEmail;

	@Column(name = "account_phone")
	private String accountPhone;
	
	@Column(name = "account_address")
	private String accountAddress;

	@Column(name = "account_disable")
	private boolean accountDisable;

	@Column(name = "account_createAt")
	private Date accountCreateAt;
	
	@Column(name = "role")
	private Long role;

	public Account() {
	}

	public Long getAccountId() {
		return accountId;
	}

	public void setAccountId(Long accountId) {
		this.accountId = accountId;
	}

	public String getAccountName() {
		return accountName;
	}

	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}

	public String getAccountUsername() {
		return accountUsername;
	}

	public void setAccountUsername(String accountUsername) {
		this.accountUsername = accountUsername;
	}

	public String getAccountPassword() {
		return accountPassword;
	}

	public void setAccountPassword(String accountPassword) {
		this.accountPassword = accountPassword;
	}

	public String getAccountEmail() {
		return accountEmail;
	}

	public void setAccountEmail(String accountEmail) {
		this.accountEmail = accountEmail;
	}

	public String getAccountPhone() {
		return accountPhone;
	}

	public void setAccountPhone(String accountPhone) {
		this.accountPhone = accountPhone;
	}

	public String getAccountAddress() {
		return accountAddress;
	}

	public void setAccountAddress(String accountAddress) {
		this.accountAddress = accountAddress;
	}

	public boolean isAccountDisable() {
		return accountDisable;
	}

	public void setAccountDisable(boolean accountDisable) {
		this.accountDisable = accountDisable;
	}

	public Date getAccountCreateAt() {
		return accountCreateAt;
	}

	public void setAccountCreateAt(Date accountCreateAt) {
		this.accountCreateAt = accountCreateAt;
	}

	public Long getRole() {
		return role;
	}

	public void setRole(Long role) {
		this.role = role;
	}

	
}
