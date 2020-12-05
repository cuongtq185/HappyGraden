package vn.com.unit.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import vn.com.unit.entity.Account;
import vn.com.unit.entity.Role;
import vn.com.unit.repository.RoleRepository;
import vn.com.unit.service.RoleService;

@Service
@Transactional
public class RoleServiceImpl implements RoleService {

	@Autowired
	RoleRepository roleRepository;
	
	@Override
	public List<GrantedAuthority> findAuthorities(Account account) {
		List<GrantedAuthority> authorities = new ArrayList<>();

		try {
			List<Role> roles = this.findRoleByAccount(account);

			for (Role role : roles) {
				authorities.add(new SimpleGrantedAuthority(role.getRoleName()));
			}
		} catch (Exception e) {
			// TODO: handle exception
		}

		return authorities;
	}
	//tìm quyền bởi account
	@Override
	public List<Role> findRoleByAccount(Account account) {
		try {
			return this.findRoleByAccountId(account.getAccountId());
		} catch (Exception e) {
			// TODO: handle exception
		}
		return new ArrayList<Role>();
	}
	
	// tìm quyền bởi id
	@Override
	public List<Role> findRoleByAccountId(Long accountId) {
		try {
			return roleRepository.findRoleByAccountId(accountId);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return new ArrayList<Role>();
	}
	// tìm quyền bởi tên quyền
	@Override
	public Long findRoleIdByName(String role_name){
		try {
			Long i = roleRepository.findRoleIdByName(role_name); 
			return i;
		} catch (Exception e) {
			// TODO: handle exception
		}
		return null;
	}
}
