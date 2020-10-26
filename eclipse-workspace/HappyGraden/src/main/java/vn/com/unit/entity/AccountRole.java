package vn.com.unit.entity;

import jp.sf.amateras.mirage.annotation.Column;
import jp.sf.amateras.mirage.annotation.Table;

@Table(name = "p2p_account_role")
public class AccountRole {

	@Column(name = "account")
	private Long account;

	@Column(name = "role")
	private Long role;
	
	public AccountRole() {
	}

	public Long getAccount() {
		return account;
	}

	public void setAccount(Long account) {
		this.account = account;
	}

	public Long getRole() {
		return role;
	}

	public void setRole(Long role) {
		this.role = role;
	}

}