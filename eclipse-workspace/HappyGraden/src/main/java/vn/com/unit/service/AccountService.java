package vn.com.unit.service;

import java.util.List;

import vn.com.unit.dto.AccountWithRoleDto;
import vn.com.unit.entity.Account;

public interface AccountService {
	
	public Account findByUsername(String username);
	
	public boolean checkLogin(Account account, String rawPassword);
	
	public boolean checkPass(Account account, String oldPassword);
		
	//tìm tất cả account 
	public List<Account> findAllAccount(int limit,int offset,String keyword,Long role_id);
	
	public Account createNewAccount(Account account,String role_name);
	
	public void setRoleByAccountId(Long id_account, Long id_role);
	
	public Account findCurrentAccount();

	public void setAccountPassword(Long account_id, String password);
	
	//tìm tài khoản bởi id
	public Account findAccountById(Long id);
	
	//đếm tất cả tài khoản còn hoạt động
	public int countAccountActive(String keyword,Long role_id);
	
	public boolean setDisableAccount(Long account_id, Long disable);

	//lưu tài khoản
	public Account save(Account account);
	
}
