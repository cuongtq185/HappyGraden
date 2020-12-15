package vn.com.unit.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import vn.com.unit.dto.CartDto;
import vn.com.unit.entity.Account;
import vn.com.unit.service.AccountService;
import vn.com.unit.service.CartService;
import vn.com.unit.service.CategoryService;
import vn.com.unit.utils.CommonUtils;

@Controller
public class CartController {

	@Autowired
	CartService cartService;

	@Autowired
	CategoryService categoryService;
	
	@Autowired
	AccountService accountService;

	@GetMapping("/cart")
	public ModelAndView cart(Model model) {

//		select cart.*, product.*
//		from
//			(
//			select *
//			from p2p_cart
//			where account = 3
//			) cart
//		left join p2p_product product
//		on product.id = cart.product

		int total_cart_item= 0;
		Long total = 0L;
		Account account = accountService.findCurrentAccount();
		total_cart_item = cartService.countAllCartItemByCurrentAccount(account.getAccountId());
		model.addAttribute("total_cart_item", total_cart_item);

		
		model.addAllAttributes(CommonUtils.getMapHeaderAtribute(model, categoryService));

		List<CartDto> list_cart_item = cartService.findAllCartItemByCurrentAccount();

		model.addAttribute("list_cart_item", list_cart_item);

		total = cartService.calculateCartTotalByCurrentAccount();

		model.addAttribute("total", total);
		model.addAttribute("total_price", total);

		model.addAttribute("title", "Cart");
		return new ModelAndView("cart");
	}

//	18:22:16,779 WARN  [org.springframework.web.servlet.mvc.support.DefaultHandlerExceptionResolver] (default task-10) Resolved exception caused by handler execution: org.springframework.http.converter.HttpMessageNotReadableException: JSON parse error: Cannot construct instance of `java.util.LinkedHashMap` (although at least one Creator exists): no String-argument constructor/factory method to deserialize from String value ('{"product_id" : 2}'); nested exception is com.fasterxml.jackson.databind.exc.MismatchedInputException: Cannot construct instance of `java.util.LinkedHashMap` (although at least one Creator exists): no String-argument constructor/factory method to deserialize from String value ('{"product_id" : 2}')
//	 at [Source: (PushbackInputStream); line: 1, column: 1]


//	public ResponseEntity<String> add(Model model, @RequestBody Map<String, String> json) {
	@PutMapping("/cart/add")
	@ResponseBody
	public ResponseEntity<String> add(Model model, @RequestBody Map<String, String> json) {
		
//		insert into p2p_cart (account, product, quantity) values (3, 1, 1);
		
		int quantity = 1;
		
		if (json.get("quantity") != null) {
			quantity = Integer.valueOf(json.get("quantity"));
		}
		
		cartService.addCartItemCurrentAccount(Long.valueOf(json.get("product_id")), quantity);
		Account account = accountService.findCurrentAccount();
		int total_cart_item = cartService.countAllCartItemByCurrentAccount(account.getAccountId());
		Long total = cartService.calculateCartTotalByCurrentAccount();
		return ResponseEntity.ok(
				"{\"msg\" : \"Add product succes! Please check again!\", \"total_item\" : \""+total_cart_item+"\", \"total\" : \""+total+"\"  }");
	}
	
	
	@DeleteMapping("/cart/deletee")
	@ResponseBody
	public ResponseEntity<String> delete(Model model, @RequestBody Map<String, String> json) {
		
		Long curent_account_id = accountService.findCurrentAccount().getAccountId();
		cartService.deleteCartItemCurrentAccount(Long.valueOf(json.get("product_id")), curent_account_id);
		
		return ResponseEntity.ok(
				"{\"msg\" : \"Delete product succes! Please check again!\" }");
	}

	@GetMapping("/cart/add/{productId}")
	@ResponseBody
	public ResponseEntity<String> addProductIntoCart(@PathVariable Long productId) {
		cartService.addCartItemCurrentAccount(productId, 1);
		return ResponseEntity.ok("{}");
	}
}
