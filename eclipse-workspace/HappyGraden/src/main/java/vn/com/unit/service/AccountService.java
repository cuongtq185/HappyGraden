package vn.com.unit.service;

import java.util.List;

import vn.com.unit.dto.AccountWithRoleDto;
import vn.com.unit.entity.Account;

public interface AccountService {
	
	public Account findByUsername(String username);
	
	public boolean checkLogin(Account account, String rawPassword);
	
	public boolean checkPass(Account account, String oldPassword);
	
	public List<AccountWithRoleDto> findAllAccount(int limit,int offset,String keyword,Long role_id);
	
	public Account createNewAccount(Account account,String role_name);
	
	public void setRoleByAccountId(Long id_account, Long id_role);
	
	public Account findCurrentAccount();

	public void setAccountPassword(Long account_id, String password);
	
//	public void saveAccount(Long account_id, String name, String email, String phone);	
//	public void saveAccountV2(Account account);	

	public Account findAccountById(Long id);
	
	public int countAccountActive(String keyword,Long role_id);
	
	public boolean setDisableAccount(Long account_id, Long disable);

	public Account save(Account account);
	
}
