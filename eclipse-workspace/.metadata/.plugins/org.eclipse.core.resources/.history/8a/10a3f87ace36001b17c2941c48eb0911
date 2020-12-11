package vn.com.unit.dto;

import java.util.List;

import vn.com.unit.entity.Account;
import vn.com.unit.entity.Role;

public class AccountWithRoleDto extends Account {

	public AccountWithRoleDto(Account account) {
		//super(account);
	}

	List<Role> roles;

	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}

	public Account extractAccount() {
		return (Account) this;
	}

}
