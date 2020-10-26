package vn.com.unit.controller.shop;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import vn.com.unit.dto.BillItemDto;
import vn.com.unit.dto.BillSeparateShopViewDto;
import vn.com.unit.dto.ProductDto;
import vn.com.unit.entity.Account;
import vn.com.unit.entity.Brand;
import vn.com.unit.entity.Category;
import vn.com.unit.entity.Product;
import vn.com.unit.entity.Shop;
import vn.com.unit.pageable.PageRequest;
import vn.com.unit.service.AccountService;
import vn.com.unit.service.AnalystService;
import vn.com.unit.service.BillItemService;
import vn.com.unit.service.BillSeparateService;
import vn.com.unit.service.BillService;
import vn.com.unit.service.BrandService;
import vn.com.unit.service.CategoryService;
import vn.com.unit.service.ProductService;
import vn.com.unit.service.RoleService;
import vn.com.unit.service.ShopService;

@Controller
@PreAuthorize("hasRole('ROLE_VENDOR')")
public class ShopController {

	@Autowired
	AccountService accountService;

	@Autowired
	RoleService roleService;

	@Autowired
	ShopService shopService;

	@Autowired
	ProductService productService;

	@Autowired
	BillService billService;

	@Autowired
	BillItemService billItemService;
	
	@Autowired
	BrandService brandService;
	
	@Autowired
	CategoryService categoryService;
	
	@Autowired
	BillSeparateService billSeparateService;
	
	@Autowired
	AnalystService analystService;

	// home view
	@GetMapping("/shop/home")
	public ModelAndView shopHome(Model model) {
		Account account = accountService.findCurrentAccount();
		Long shop_id = account.getId();
		Shop shop = shopService.findShopByAccountId(shop_id);
		
		Long revenue = analystService.caculateShopRevenueLastDayByShopId(shop_id);
		
		if (revenue == null) {
			revenue = 0L;
		}
		
		model.addAttribute("revenue", revenue);
		
		Long bill_error = analystService.caculateBillPaymentErrorLastDayByShopId(shop_id);
		
		if (bill_error == null) {
			bill_error = 0L;
		}
		
		model.addAttribute("bill_error", bill_error);
		
		if(shop == null) {
			return new ModelAndView("shop/myShop/create-shop");
		}
		
		model.addAttribute("shop", shop);
		model.addAttribute("title", "Shop Management");
		return new ModelAndView("shop/shop");
	}

	// edit shop view
	@GetMapping("/shop/myshop")
	public ModelAndView editShop(Model model) {

		Account account = accountService.findCurrentAccount();
		Shop shop = shopService.findShopByAccountId(account.getId());

		model.addAttribute("shop", shop);
		model.addAttribute("title", "Shop Management");

		return new ModelAndView("shop/myShop/editShop");
	}
	
	//product view
	@GetMapping("/shop/myproduct")
	public ModelAndView product(
			Model model,
			@RequestParam(name = "mode") String mode,
			@RequestParam(value = "page", required = false, defaultValue = "1") int page,
			@RequestParam(value = "limit", required = false, defaultValue = "10") int limit,
			HttpServletRequest request
			) {
		String type = "";
		if(mode.equals("create")) {
			 type = "shop/myProduct/shop-add-product";
		}
		if(mode.equals("edit")) {
			 type = "shop/myProduct/shop-edit-product";
		}
		if(mode.equals("view")) {
			 type = "shop/myProduct/shop-product";
		}
		
		
		
		Account account = accountService.findCurrentAccount();	
		int totalitems = productService.CountAllProductByShopId((long) account.getId());
		int totalpages = (int) Math.ceil((double) totalitems / (double) limit);
		Shop shop = shopService.findShopByAccountId(account.getId());
		model.addAttribute("shop", shop);
		PageRequest pageable = new PageRequest(page, limit, totalitems, totalpages);
		
		List<ProductDto> products = productService.findAllProductByShopId(account.getId(),pageable.getLimit(), pageable.getOffset());
		
		
		List<Brand> brands = brandService.findAllBrand();
		List<Category> categories = categoryService.findAllCategory();
		model.addAttribute("products", products);
		model.addAttribute("title", "Shop Management");
		
		model.addAttribute("pageable", pageable);

		model.addAttribute("brands", brands);
		model.addAttribute("categories", categories);
		model.addAttribute("new_product", new Product());
		return new ModelAndView(type);
	}
	
	//edit product view
	@PostMapping("/shop/myproduct")
	public ModelAndView editProduct(Model model, @RequestParam(name = "product_id") Long product_id) {
		Product product = productService.findProductByProductId(product_id);
		//Account account = accountService.findCurrentAccount();	
		//List<Product> products = productService.findAllProductByShopId(account.getId());
		List<Brand> brands = brandService.findAllBrand();
		List<Category> categories = categoryService.findAllCategory();
		model.addAttribute("product", product);
		model.addAttribute("title", "Shop Management");
		Account account = accountService.findCurrentAccount();	
		Shop shop = shopService.findShopByAccountId(account.getId());
		model.addAttribute("shop", shop);

		model.addAttribute("brands", brands);
		model.addAttribute("categories", categories);
		return new ModelAndView("shop/myProduct/shop-edit-product");
	}

	@GetMapping("/shop/mybills")
	public ModelAndView bills(
			Model model,
    		@RequestParam(value = "page", required = false, defaultValue = "1") int page,
			@RequestParam(value = "limit", required = false, defaultValue = "8") int limit,
			HttpServletRequest request
			) {
		
		
		Account account = accountService.findCurrentAccount();	
		Shop shop = shopService.findShopByAccountId(account.getId());
		model.addAttribute("shop", shop);
		Long status = (long) 0;
		Long payment = (long) 0;
		int totalitems = billSeparateService.countBillSeparateByPaymentAndStatusAndShopId(payment, status, shop.getId());
		int totalpages = (int) Math.ceil((double) totalitems / (double) limit);
		PageRequest pageable = new PageRequest(page, limit, totalitems, totalpages);
		List<BillSeparateShopViewDto> bills = billSeparateService.findBillSeparateByPaymentAndStatusAndShopId(payment, status, shop.getId(),pageable.getLimit(),pageable.getOffset());
		for (BillSeparateShopViewDto bill : bills) {
			int total = billItemService.totalPriceOfBillByBillSeparateId(bill.getId());
			bill.setTotalPrice(total);
		}
		model.addAttribute("bills", bills);
		model.addAttribute("pageable", pageable);
		return new ModelAndView("shop/myBill/waiting-payment");
	}
	
	@GetMapping("/shop/mybill/{bill-separate-id}")
	public ModelAndView billDeatil(Model model,@PathVariable("bill-separate-id") Long bill_separate_id
			) {
		Account account = accountService.findCurrentAccount();	
		Shop shop = shopService.findShopByAccountId(account.getId());
		model.addAttribute("shop", shop);
		model.addAttribute("id_bill", bill_separate_id);
		List<BillItemDto> billitems = billItemService.findAllBillItemByBillSeparateId(bill_separate_id);
		int total = billItemService.totalPriceOfBillByBillSeparateId(bill_separate_id);
		model.addAttribute("billitems", billitems);
		model.addAttribute("total_price", total);
		return new ModelAndView("shop/myBill/bill-detail");
	}
	
}
