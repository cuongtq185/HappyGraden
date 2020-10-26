package vn.com.unit.controller.admin;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import vn.com.unit.dto.AccountWithRoleDto;
import vn.com.unit.entity.Account;
import vn.com.unit.entity.Shop;
import vn.com.unit.pageable.PageRequest;
import vn.com.unit.service.AccountService;
import vn.com.unit.service.ShopService;

@Controller
@PreAuthorize("hasRole('ROLE_ADMIN')")
public class AdminAccountManagementController {

	@Autowired
	private AccountService accountService;
	@Autowired
	private ShopService shopService;
	
	@GetMapping("/admin/account/list")
	public ModelAndView accountList(Model model,
			@RequestParam(value = "page", required = false, defaultValue = "1") int page,
			@RequestParam(value = "limit", required = false, defaultValue = "10") int limit,
			@RequestParam(value = "keyword", required = false) String keyword,
			@RequestParam(value = "role_id", required = false) Long role_id,
			HttpServletRequest request) {

		int totalitems = accountService.countAccountActive(keyword,role_id);
		int totalpages = (int) Math.ceil((double) totalitems / (double) limit);

		PageRequest<AccountWithRoleDto> pageable = new PageRequest<AccountWithRoleDto>(page, limit, totalitems, totalpages);

		List<AccountWithRoleDto> accounts = accountService.findAllAccount(pageable.getLimit(), pageable.getOffset(),keyword,role_id);
		pageable.setData(accounts);
		if(keyword != null) {
			model.addAttribute("keyword", keyword);

		}
		model.addAttribute("pageable", pageable);

		return new ModelAndView("admin/account/account-table");
	}
	
	
	@GetMapping("/admin/account/list-ajax")
	public  ModelAndView  accountListAjax(Model model,
			@RequestParam(value = "page", required = false, defaultValue = "1") int page,
			@RequestParam(value = "limit", required = false, defaultValue = "10") int limit,
			@RequestParam(value = "keyword", required = false) String keyword,
			@RequestParam(value = "role_id", required = false) Long role_id,
			HttpServletRequest request) {

		int totalitems = accountService.countAccountActive(keyword,role_id);
		int totalpages = (int) Math.ceil((double) totalitems / (double) limit);

		
		PageRequest<AccountWithRoleDto> pageable = new PageRequest<AccountWithRoleDto>(page, limit, totalitems, totalpages);
		List<AccountWithRoleDto> accounts = accountService.findAllAccount(pageable.getLimit(), pageable.getOffset(),keyword,role_id);
		
		model.addAttribute("accounts", accounts);
		model.addAttribute("pageable", pageable);
		model.addAttribute("keyword", keyword);
		pageable.setData(accounts);
		return new ModelAndView("components/admin/account/account-list");

	}
	@GetMapping("/admin/account/{account_id}")
	public ModelAndView accountList(Model model,
			 @PathVariable("account_id") Long account_id,
			HttpServletRequest request) {
		
		Account account = accountService.findAccountById(account_id);
		Shop shop = shopService.findShopByAccountId(account_id);
		model.addAttribute("account",account);
		model.addAttribute("shop",shop);

		return new ModelAndView("admin/account/account-view");
	}
	
	@GetMapping("/admin/account/add")
	public ModelAndView accountAdd(Model model,
			HttpServletRequest request) {
		
		return new ModelAndView("admin/account/add-account");
	}
	
	@PostMapping("/admin/account/add")
	@ResponseBody
	public ResponseEntity<String> createAccount(@RequestBody Account account, Model model) {
		if (account.getUsername() == null || account.getUsername().equals("")) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{ \"msg\" : \"Username cannot be empty\" }");
		}

		if (account.getPassword() == null || account.getPassword().equals("")) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{ \"msg\" : \"Password cannot be empty\" }");
		}

		if (account.getPassword().length() < 8) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
					.body("{ \"msg\" : \"Password too short - minimum length is 8 characters\" }");
		}

		if (accountService.findByUsername(account.getUsername()) != null) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{ \"msg\" : \"Username already exists\" }");
		}

		Account account_new = accountService.createNewAccount(account, "ROLE_ADMIN");

		if (account_new != null) {
			return ResponseEntity.ok(
					"{ \"id\" : " + account_new.getId().toString() + ", \"msg\" : \"Create account successfully\" }");
		}

		return ResponseEntity.status(HttpStatus.BAD_REQUEST)
				.body("{ \"msg\" : \"You can't create an account right now. Try again later\" }");
	}
	
	@DeleteMapping("/admin/account/delete/{account_id}")
	public ResponseEntity<Boolean> AdminDisableShop(Model model, @PathVariable("account_id") Long account_id,
			HttpServletRequest request) {
		if (accountService.setDisableAccount(account_id, (long) 1)) {
			return ResponseEntity.status(HttpStatus.OK).body(null);
		}
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
	}
	

}
