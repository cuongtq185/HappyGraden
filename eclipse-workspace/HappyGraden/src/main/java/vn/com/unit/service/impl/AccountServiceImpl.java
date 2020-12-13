package vn.com.unit.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import vn.com.unit.dto.AccountWithRoleDto;
import vn.com.unit.entity.Account;
import vn.com.unit.entity.AccountRole;
import vn.com.unit.entity.Role;
import vn.com.unit.repository.AccountRepository;
import vn.com.unit.repository.AccountRoleRepository;
import vn.com.unit.repository.RoleRepository;
import vn.com.unit.service.AccountService;
import vn.com.unit.service.RoleService;
import vn.com.unit.utils.CommonUtils;

@Service
@Transactional
public class AccountServiceImpl implements AccountService {

	@Autowired
	private AccountRepository accountRepository;

	@Autowired
	private RoleService roleService;

	@Autowired
	AccountService accountService;

	@Autowired
	RoleRepository roleRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private AccountRoleRepository accountRoleRepository;

	@Override
	public Account findByUsername(String username) {
		return accountRepository.findByUsername(username);
	}

	@Override
	public boolean checkLogin(Account account, String rawPassword) {
		try {

			String encodedPassword = account.getAccountPassword();
			if (encodedPassword.equals("")) {
				String defaultRawPassword = CommonUtils.DEFAULT_PASSWORD;

				Account account_temp = accountRepository.findOne(account.getAccountId());
				account_temp.setAccountPassword(CommonUtils.encodePassword(defaultRawPassword));
				accountRepository.save(account_temp);
				return rawPassword.equals(defaultRawPassword);
			}

			return passwordEncoder.matches(rawPassword, encodedPassword);

		} catch (Exception e) {
			// TODO: handle exception
		}

		return false;
	}

	// check password
	@Override
	public boolean checkPass(Account account, String oldPassword) {
		try {

			String encodedPassword = account.getAccountPassword();
			return passwordEncoder.matches(oldPassword, encodedPassword);

		} catch (Exception e) {
			// TODO: handle exception
		}

		return false;
	}

	// tìm tất cả account kèm role
	@Override
	public List<Account> findAllAccount(int limit, int offset, String keyword, Long role_id) {
		List<Account> accounts = new ArrayList<Account>();
		//List<AccountWithRoleDto> account_role_dto_list = new ArrayList<AccountWithRoleDto>();
		try {
			accounts = accountRepository.findAllAccountActive(limit, offset, keyword, role_id);

//			for (Account account : accounts) {
//				List<Role> roles = roleService.findRoleByAccountId(account.getAccountId());
//
//				AccountWithRoleDto account_role_dto = new AccountWithRoleDto(account);
//
//				account_role_dto.setRoles(roles);
//				//account_role_dto_list.add(account_role_dto);
//			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return accounts;
	}

	// create new account
	@Override
	public Account createNewAccount(Account account, String role_name) {
		try {
			String username = account.getAccountName();

			if (username.equals("")) {
				return null;
			}

			String password = CommonUtils.encodePassword(account.getAccountPassword());

			Account account_temp = new Account();
			account_temp.setAccountUsername(username);
			account_temp.setAccountPassword(password);;

			Account account_new = accountRepository.save(account_temp);

			if (account_new != null) {

				Account account_role_new = new Account();
				
				account_role_new.setAccountId(account_new.getAccountId());
				account_role_new.setRole(roleService.findRoleIdByName(role_name));

				accountRoleRepository.save(account_role_new);
				
				return account_new;
			}

		} catch (Exception e) {

		}
		return null;
	}
	// getIDsetRole

	@Override
	public void setRoleByAccountId(Long account_id, Long role_id) {
		try {
			Account account_role_temp = new Account();
			account_role_temp.setAccountId(account_id);
			account_role_temp.setRole(role_id);

			accountRoleRepository.save(account_role_temp);
		} catch (Exception e) {
			// TODO: handle exception
		}

	}

	// getCurrentAccount
	@Override
	public Account findCurrentAccount() {
		String currentUsername = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
		Account account = findByUsername(currentUsername);
		AccountWithRoleDto account_role_dto = new AccountWithRoleDto(account);
		List<Role> roles = roleService.findRoleByAccount(account);
		account_role_dto.setRoles(roles);
		return account_role_dto;
	}

	// setPassword
	@Override
	public void setAccountPassword(Long account_id, String password) {
		try {
			password = CommonUtils.encodePassword(password);
			
			Account account_temp = accountRepository.findOne(account_id);
			
			account_temp.setAccountPassword(password);
			
			accountRepository.save(account_temp);
			
		} catch (Exception e) {

		}

	}

    //tìm tài khoản bởi id
	@Override
	public Account findAccountById(Long id) {
		try {
			return accountRepository.findAccountById(id);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return null;
	}
	
	//đếm tất cả tài khoản còn hoạt động
	@Override
	public int countAccountActive(String keyword, Long role_id) {
		try {
			return accountRepository.countAccountActive(keyword, role_id);
		} catch (Exception e) {
			// TODO: handle exception
		}
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean setDisableAccount(Long account_id, Long disable) {
		try {
			accountRepository.setDisableAccount(account_id, disable);
			return true;
		} catch (Exception e) {

		}
		return false;
	}


	@Override
	public Account save(Account account) {
		return accountRepository.save(account);
	}

}
