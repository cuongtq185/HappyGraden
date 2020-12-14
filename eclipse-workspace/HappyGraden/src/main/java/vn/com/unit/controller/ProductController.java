package vn.com.unit.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

import vn.com.unit.entity.Account;
import vn.com.unit.entity.Category;
import vn.com.unit.entity.Product;
import vn.com.unit.service.AccountService;
import vn.com.unit.service.CartService;
import vn.com.unit.service.CategoryService;
import vn.com.unit.service.ProductService;
import vn.com.unit.utils.CommonUtils;

@Controller
public class ProductController {

	@Autowired
	ProductService productService;
	@Autowired
	CategoryService categoryService;
	
	@Autowired
	private CartService cartService;
	
	@Autowired
	private AccountService accountService;
	
	@GetMapping("/product/{product_id}")
    public ModelAndView home(Model model, @PathVariable ("product_id") Long product_id) {
		
		model.addAllAttributes(CommonUtils.getMapHeaderAtribute(model, categoryService));
		
		Product product = productService.findProductByProductId(product_id);
		model.addAttribute("product", product);
		Long id = (long) product.getCategory();
		List<Product> products = productService.findAllProductByCategoryIdNotPagination(id);
		model.addAttribute("products", products);
		List<Category> categories = categoryService.findAllCategory();
		model.addAttribute("categories", categories);
//		model.addAttribute("title", product.getName());
		
		int total_cart_item= 0;
		Long total = 0L;
		Account account = accountService.findCurrentAccount();
		//total_cart_item = cartService.countAllCartItemByCurrentAccount(account.getId());
		model.addAttribute("total_cart_item", total_cart_item);
		
		total = cartService.calculateCartTotalByCurrentAccount();
		model.addAttribute("total_price", total);
        return new ModelAndView("product-detail");
    }
	
}
