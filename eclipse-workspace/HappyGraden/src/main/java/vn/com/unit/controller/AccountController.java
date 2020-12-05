package vn.com.unit.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import vn.com.unit.entity.Account;
import vn.com.unit.service.AccountService;

@Controller
public class AccountController {

	@Autowired
	AccountService accountService;
	
	

	@PostMapping("/register")
	@ResponseBody
	public ResponseEntity<String> createAccount(@RequestBody Account account, Model model) {
		if (account.getAccountUsername() == null || account.getAccountUsername().equals("")) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{ \"msg\" : \"Username cannot be empty\" }");
		}

		if (account.getAccountPassword() == null || account.getAccountPassword().equals("")) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{ \"msg\" : \"Password cannot be empty\" }");
		}

		if (account.getAccountPassword().length() < 8) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
					.body("{ \"msg\" : \"Password too short - minimum length is 8 characters\" }");
		}

		if (accountService.findByUsername(account.getAccountUsername()) != null) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{ \"msg\" : \"Username already exists\" }");
		}

		Account account_new = accountService.createNewAccount(account, "ROLE_USER");

		if (account_new != null) {
			return ResponseEntity.ok(
					"{ \"id\" : " + account_new.getAccountId().toString() + ", \"msg\" : \"Create account successfully\" }");
		}

		return ResponseEntity.status(HttpStatus.BAD_REQUEST)
				.body("{ \"msg\" : \"You can't create an account right now. Try again later\" }");
	
	}
	
	
	
}
