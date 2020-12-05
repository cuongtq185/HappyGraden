////package vn.com.unit.controller.profile;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.access.prepost.PreAuthorize;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.servlet.ModelAndView;
//
//import vn.com.unit.entity.Account;
//import vn.com.unit.entity.Shop;
//import vn.com.unit.service.AccountService;
//import vn.com.unit.service.BillItemService;
//import vn.com.unit.service.BillService;
//import vn.com.unit.service.ProductService;
//import vn.com.unit.service.RoleService;
//import vn.com.unit.service.ShopService;
//
//@Controller
//public class ProfileController {
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
//	@Autowired
//	ProductService productService;
//	
//	@Autowired
//	BillService billService;
//	
//	@Autowired
//	BillItemService billItemService;
//
//	
//	// home view
//	@PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_VENDOR', 'ROLE_ADMIN')")
//	@GetMapping("/profile/home")
//	public ModelAndView home(Model model) {
//		model.addAttribute("title", "Account Management");
//		return new ModelAndView("profile/profile");
//	}
//	
//	// create shop view
//	@PreAuthorize("hasAnyRole('ROLE_USER','ROLE_VENDOR')")
//	@GetMapping("/profile/createshop")
//	public ModelAndView createShop(Model model) {
//		Account account = accountService.findCurrentAccount();
//		Shop shop = shopService.findShopByAccountId(account.getId());
//		if(shop == null) {
//			model.addAttribute("title", "Account Management");
//			return new ModelAndView("profile/shop/create-shop");
//		}
//		if(shop.getStatus() == 1) {
//			model.addAttribute("title", "Account Management");
//			return new ModelAndView("profile/shop/exisist-shop");
//		}
//		if(shop.getStatus() == 2 ||shop.getStatus() == 3) {
//			model.addAttribute("title", "Account Management");
//			return new ModelAndView("profile/shop/activate-shop");
//		}
//		model.addAttribute("title", "Account Management");
//		return new ModelAndView("profile/shop/wait-confirm-shop");
//	}
//	
//	
//	//editAccount view
//	@PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_VENDOR', 'ROLE_ADMIN')")
//	@GetMapping("/profile/myaccount")
//	public ModelAndView myAccount(Model model,@RequestParam(name = "mode") String mode) {
//		String type = "";
//		if(mode.equals("editPass")) {
//			 type = "profile/myAccount/editPass";
//		}else {
//			type = "profile/myAccount/account-table";
//		}		
//		Account account = accountService.findCurrentAccount();	
//		model.addAttribute("current_account", account);
//		model.addAttribute("title", "Account Management");
//		return new ModelAndView(type); }
//	
//	
//	
//	
//}
