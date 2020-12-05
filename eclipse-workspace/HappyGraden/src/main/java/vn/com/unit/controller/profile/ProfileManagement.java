//package vn.com.unit.controller.profile;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.security.access.prepost.PreAuthorize;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PutMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.ResponseBody;
//
//import vn.com.unit.entity.Account;
//import vn.com.unit.service.AccountService;
//import vn.com.unit.service.RoleService;
//import vn.com.unit.service.ShopService;
//
//@Controller
//@PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_VENDOR', 'ROLE_ADMIN')")
//
//public class ProfileManagement {
//
//	@Autowired
//	AccountService accountService;
//
//	@Autowired
//	RoleService roleService;
//
//	@Autowired
//	ShopService shopService;
//
//	// edit account
//	@PutMapping("/account")
//	@ResponseBody
//	public ResponseEntity<Account> editAccount(@RequestBody Account account, Model model) {
//
//		Account account_current = accountService.findCurrentAccount();
//
//		account_current.setPhone(account.getPhone());
//		account_current.setEmail(account.getEmail());
//		account_current.setName(account.getName());
//
//		Account account_new = accountService.save(account_current);
//
//		return ResponseEntity.ok(account_new);
//
//	}
//
//	// edit password
//
//	@PutMapping("/password/{old_password}")
//	@ResponseBody
//	public ResponseEntity<String> editPass(@RequestBody Account new_account, Model model,
//			@PathVariable("old_password") String old_password) {
//		Account account = accountService.findCurrentAccount();
//
//		if (new_account.getPassword() == null || new_account.getPassword().equals("")) {
//			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{ \"msg\" : \"Password cannot be empty\" }");
//		}
//
//		if (new_account.getPassword().length() < 8) {
//			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
//					.body("{ \"msg\" : \"Password too short - minimum length is 8 characters\" }");
//		}
//
//		boolean test = accountService.checkPass(account, old_password);
//
//		if (test == true) {
//			accountService.setAccountPassword(account.getId(), new_account.getPassword());
//			return ResponseEntity.status(HttpStatus.OK).body("{ \"msg\" : \"Change Passowrd success!\" }");
//		}
//		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{ \"msg\" : \"Password do not match!\" }");
//	}
//
//}
