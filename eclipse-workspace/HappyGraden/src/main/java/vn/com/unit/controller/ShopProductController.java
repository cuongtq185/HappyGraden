//package vn.com.unit.controller;
//
//import java.util.List;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.servlet.ModelAndView;
//
//import vn.com.unit.entity.Account;
//import vn.com.unit.entity.Brand;
//import vn.com.unit.entity.Category;
//import vn.com.unit.entity.Product;
//import vn.com.unit.entity.Shop;
//import vn.com.unit.pageable.PageRequest;
//import vn.com.unit.service.AccountService;
//import vn.com.unit.service.BrandService;
//import vn.com.unit.service.CartService;
//import vn.com.unit.service.CategoryService;
//import vn.com.unit.service.ProductService;
//import vn.com.unit.service.ShopService;
//import vn.com.unit.utils.CommonUtils;
//
//@Controller
//public class ShopProductController {
//
//	@Autowired
//	ProductService productService;
//	@Autowired
//	ShopService shopService;
//	@Autowired
//	BrandService brandService;
//	@Autowired
//	CategoryService categoryService;
//	@Autowired
//	private CartService cartService;
//	
//	@Autowired
//	private AccountService accountService;
//	
//	@GetMapping("/shop/{shop_id}")
//    public ModelAndView home(Model model, 
//    		@PathVariable ("shop_id") Long shop_id, 
//    		@RequestParam(value = "category",required=false) Long category_id, 
//    		@RequestParam(value = "brand",required=false) Long brand_id,
//    		@RequestParam(value = "page", required = false, defaultValue = "1") int page,
//			@RequestParam(value = "limit", required = false, defaultValue = "12") int limit
//    		) {
//			//get all infor of shop
//			Shop shop = shopService.findShopByAccountId(shop_id);
//			model.addAttribute("shop", shop);
//			
//			if(shop == null || shop.getStatus() == 2 || shop.getStatus() == 3) {
//				return new ModelAndView("404");
//			}
//			
//			//get brand and category
//			model.addAllAttributes(CommonUtils.getMapHeaderAtribute(model, categoryService));
//			List<Brand> brands = brandService.findAllBrandByShopId(shop.getId());
//			List<Category> categories_shop = categoryService.findAllCategoryByShopId(shop.getId());
//			model.addAttribute("brands_shop", brands);
//			model.addAttribute("categories_shop", categories_shop);
//
//
//				int totalitems = productService.countAllProductByCategoryIdAndBrandId(category_id, brand_id,shop_id);
//				PageRequest pageable = new PageRequest(page, limit, totalitems);
//				
//				List<Product> products = productService.findAllProductByCategoryIdAndBrandId(category_id, brand_id, shop_id, pageable.getLimit(),pageable.getOffset());
//				
//				int total = productService.countAllProductByCategoryIdAndBrandId(category_id, brand_id, shop_id);
//				
//				int total_cart_item= 0;
//				Long total_price = 0L;
//				Account account = accountService.findCurrentAccount();
//				total_cart_item = cartService.countAllCartItemByCurrentAccount(account.getId());
//				model.addAttribute("total_cart_item", total_cart_item);
//				
//				total_price = cartService.calculateCartTotalByCurrentAccount();
//				model.addAttribute("total_price", total_price);
//				model.addAttribute("total", total);
//				model.addAttribute("products", products);
//				model.addAttribute("pageable", pageable);
//				model.addAttribute("title", shop.getName());
//		        return new ModelAndView("shop");
//    }
//	
//}
