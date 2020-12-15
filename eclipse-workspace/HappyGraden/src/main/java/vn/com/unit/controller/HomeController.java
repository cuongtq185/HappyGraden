package vn.com.unit.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import vn.com.unit.dto.ProductDto;
import vn.com.unit.entity.Account;
import vn.com.unit.entity.Product;
import vn.com.unit.service.AccountService;
import vn.com.unit.service.CartService;
import vn.com.unit.service.ProductService;
import vn.com.unit.service.RoleService;
import vn.com.unit.service.ShopService;

@Controller
public class HomeController {

//	@Autowired
//	CategoryService categoryService;

	@Autowired
	ProductService productService;
	
	@Autowired
	ShopService shopService;

	@Autowired
	private RoleService roleService;

	@Autowired
	private AccountService accountService;
	
	@Autowired
	private CartService cartService;


	@GetMapping("*")
	public ModelAndView home(Model model, @Param("name") String name) {

		//model.addAllAttributes(CommonUtils.getMapHeaderAtribute(model, categoryService));

		// Add Role if reload
		int total_cart_item = 0;
		Long total = 0L;
		model.addAttribute("total_cart_item", total_cart_item);
		model.addAttribute("total_price", Math.toIntExact(total));

		try {
			Account account = accountService.findCurrentAccount();

			if (account != null) {
				Authentication auth = SecurityContextHolder.getContext().getAuthentication();

				List<GrantedAuthority> authorities = new ArrayList<>();

				authorities = roleService.findAuthorities(account);

				Authentication newAuth = new UsernamePasswordAuthenticationToken(auth.getPrincipal(),
						auth.getCredentials(), authorities);

				SecurityContextHolder.getContext().setAuthentication(newAuth);
				
//				total_cart_item = cartService.countAllCartItemByCurrentAccount(account.getAccountId());
//				model.addAttribute("total_cart_item", total_cart_item);
				
//				total = cartService.calculateCartTotalByCurrentAccount();
//				model.addAttribute("total_price", Math.toIntExact(total));
			}

		} catch (Exception e) {
			// TODO: handle exception
		}

		model.addAttribute("title", "Home");

//		List<Category> categories = categoryService.findAllCategory();
//		model.addAttribute("categories", categories);
		


//		List<Product> product = productService.findAllActiveProductOfShopActiveOfVendorActive();
//		model.addAttribute("product", product);
//		
//		Product top_product = productService.findOneTopProductPaymentSuccess();
//		model.addAttribute("top_product", top_product);
//		
//		List<Shop> shops = shopService.searchAllShop();
//		model.addAttribute("shops", shops);
		
		List<ProductDto> lstProduct = productService.findAllProductActive(8, 0);
		
		model.addAttribute("lstProduct", lstProduct);
		
		return new ModelAndView("index");
	}

//	@GetMapping("/product-by-search")
//	public ModelAndView test(Model model, @RequestParam("name") String name) {
//		
//		// Prevent sql injection
//		name = String.join("%", name.split(""));
//		
//		model.addAllAttributes(CommonUtils.getMapHeaderAtribute(model, categoryService));
//		
//		List<Product> products = productService.searchProductByName(name);
//		model.addAttribute("products", products);
//		model.addAttribute("name", name);
//		
//		List<Brand> brands = brandService.findAllBrand();
//		model.addAttribute("brands", brands);
//
//		
//		int total_cart_item= 0;
//		Long total = 0L;
//		Account account = accountService.findCurrentAccount();
//		total_cart_item = cartService.countAllCartItemByCurrentAccount(account.getAccountId());
//		model.addAttribute("total_cart_item", total_cart_item);
//		
//		total = cartService.calculateCartTotalByCurrentAccount();
//		model.addAttribute("total_price", total);
//		return new ModelAndView("product-by-search");
//	}
	
//	@GetMapping("/category")
//	public ModelAndView category(Model model, 
//			@RequestParam("id") Long id,
//    		@RequestParam(value = "page", required = false, defaultValue = "1") int page,
//			@RequestParam(value = "limit", required = false, defaultValue = "12") int limit
//			) {
//		
//		model.addAllAttributes(CommonUtils.getMapHeaderAtribute(model, categoryService));
//		
//		int totalitems = productService.countAllProductByCategoryId(id);
//		
//		Category cate = categoryService.findCategoryById(id);
//		model.addAttribute("cate", cate);
//		
//		PageRequest pageable = new PageRequest(page, limit, totalitems);
//		model.addAttribute("pageable", pageable);
//		
//		List<Product> products = productService.findAllProductByCategoryId(id, pageable.getLimit(),pageable.getOffset());
//		model.addAttribute("products", products);
//		
//		
//		int total_cart_item= 0;
//		Long total = 0L;
//		Account account = accountService.findCurrentAccount();
//		total_cart_item = cartService.countAllCartItemByCurrentAccount(account.getAccountId());
//		model.addAttribute("total_cart_item", total_cart_item);
//		
//		total = cartService.calculateCartTotalByCurrentAccount();
//		model.addAttribute("total_price", total);
//		return new ModelAndView("product-by-category");
//	}

	@GetMapping("/register")
	public ModelAndView register(Model model) {
		
		return new ModelAndView("register");
	}

	@GetMapping("/login")
	public ModelAndView login(Model model, @RequestParam(value="error",required=false) String error) {
		if(error == null) {
			model.addAttribute("error", "");
		}else {
			if(error.equals("Wrong username or password")) {
				model.addAttribute("error", "Wrong username or password!");
			}
			if(error.equals("timeout")) {
				model.addAttribute("error", "Time Out!");
			}
			if(error.equals("Your account has been deactivated")) {
				model.addAttribute("error", "Your account has been deactivated!");
			}
			if(error.equals("max_session")) {
				model.addAttribute("error", "Your account has been logged in another device!");
			}
		}


		
		model.addAttribute("title", "Login");
		return new ModelAndView("login");
	}

	@GetMapping("/logout")
	public String logout(HttpServletRequest request) {

		HttpSession httpSession = request.getSession(false);
		if (httpSession != null) {
			httpSession.invalidate();
		}

		return "redirect:/";
	}
	
	
	//search Ajax
	@PostMapping("/search-ajax")
	@ResponseBody
	public ResponseEntity<List<Product>> test(Model model, @RequestBody String key,HttpServletResponse response) {
		String key_value = key;
		List<Product> products = productService.searchProductByName(key_value);

		return ResponseEntity.ok(products);
	}

}
