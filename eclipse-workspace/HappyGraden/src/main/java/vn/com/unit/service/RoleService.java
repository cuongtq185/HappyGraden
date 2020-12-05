package vn.com.unit.service;

import java.util.List;

import org.springframework.security.core.GrantedAuthority;

import vn.com.unit.entity.Account;
import vn.com.unit.entity.Role;

public interface RoleService {
	
	public List<GrantedAuthority> findAuthorities(Account account);
	
	public List<Role> findRoleByAccount(Account account);
	
	//tìm quyển bởi id
	public List<Role> findRoleByAccountId(Long accountId);
	
	public Long findRoleIdByName(String role_name);
}
