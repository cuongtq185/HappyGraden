//package vn.com.unit.controller;
//
//import java.util.List;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.servlet.ModelAndView;
//
//import vn.com.unit.dto.BillItemDto;
//import vn.com.unit.entity.Bill;
//import vn.com.unit.service.BillItemService;
//import vn.com.unit.service.BillService;
//
//@Controller
//public class BillController {
//
//	@Autowired
//	BillService billService;
//	
//	@Autowired
//	BillItemService billItemService;
//	
//	@GetMapping("/bill/{id}")
//	public ModelAndView bill(@PathVariable ("id") Long id, Model model) {
//		
//		Bill bill = billService.findBillOfCurrentAccountByBillId(id);
//		
//		if (bill == null) {
//			return new ModelAndView("/404");
//		}
//		
//		model.addAttribute("bill", bill);
//		
//		Long total = billService.calculateBillTotal(id);
//		
//		model.addAttribute("total", total);
//		
//		List<BillItemDto> bill_item = billItemService.findAllBillItemByBillId(id);
//		
//		model.addAttribute("bill_item", bill_item);
//		
//		return new ModelAndView("/bill");
//	}
//	
//}
