//package vn.com.unit.controller.profile;
//
//import java.util.List;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.access.prepost.PreAuthorize;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.servlet.ModelAndView;
//
//import vn.com.unit.dto.BillItemDto;
//import vn.com.unit.dto.BillSeparateHistoryDTO;
//import vn.com.unit.entity.Account;
//import vn.com.unit.pageable.PageRequest;
//import vn.com.unit.service.AccountService;
//import vn.com.unit.service.BillSeparateService;
//
//@Controller
//public class ProfileBillHistoryController {
//	@Autowired
//	private AccountService accountService;
//
////	@Autowired
////	private BillService billService;
//
//	@Autowired
//	private BillSeparateService billSeparateService;
//
//	// bills view
//	@PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_VENDOR', 'ROLE_ADMIN')")
//	@GetMapping("/profile/mybills/confirm")
//	public ModelAndView confirmListBill(
//			Model model,
//			@RequestParam(value = "page", required = false, defaultValue = "1") int page,
//			@RequestParam(value = "limit", required = false, defaultValue = "10") int limit) {
//		Account account = accountService.findCurrentAccount();
//		Long status = (long) 1;
//		Long Payment = (long) 1;
//		int totalitems = billSeparateService.countBillSeparateByAccountId(account.getId(), status,Payment);
//		int totalpages = (int) Math.ceil((double) totalitems / (double) limit);
//
//		PageRequest pageable = new PageRequest(page, limit, totalitems, totalpages);
//		List<BillSeparateHistoryDTO> bills = billSeparateService.findAllBillSeparateByAccountId(account.getId(), status,Payment,pageable.getLimit(), pageable.getOffset());
//		model.addAttribute("current_account", account);
//		model.addAttribute("bills", bills);
//		model.addAttribute("pageable", pageable);
//
//		model.addAttribute("title", "Account Management");
//		return new ModelAndView("profile/myBill/confirm-bills");
//	}
//
//	@GetMapping("/profile/mybills/waiting-confirm")
//	public ModelAndView wattingBillList(Model model,
//			@RequestParam(value = "page", required = false, defaultValue = "1") int page,
//			@RequestParam(value = "limit", required = false, defaultValue = "10") int limit) {
//		Account account = accountService.findCurrentAccount();
//		Long status = (long) 0;
//		Long Payment = (long) 1;
//		int totalitems = billSeparateService.countBillSeparateByAccountId(account.getId(), status,Payment);
//		int totalpages = (int) Math.ceil((double) totalitems / (double) limit);
//
//		PageRequest pageable = new PageRequest(page, limit, totalitems, totalpages);
//		List<BillSeparateHistoryDTO> bills = billSeparateService.findAllBillSeparateByAccountId(account.getId(), status,Payment,pageable.getLimit(), pageable.getOffset());
//		model.addAttribute("current_account", account);
//		model.addAttribute("bills", bills);
//		model.addAttribute("pageable", pageable);
//
//		model.addAttribute("title", "Account Man agement");
//		return new ModelAndView("profile/myBill/waitting-confirm");
//	}
//
//	@GetMapping("/profile/mybills/waiting-payment")
//	public ModelAndView wattingPaymentList(Model model,
//			@RequestParam(value = "page", required = false, defaultValue = "1") int page,
//			@RequestParam(value = "limit", required = false, defaultValue = "10") int limit) {
//		Account account = accountService.findCurrentAccount();
//		Long status = (long) 0;
//		Long Payment = (long) 0;
//		int totalitems = billSeparateService.countBillSeparateByAccountId(account.getId(), status,Payment);
//		int totalpages = (int) Math.ceil((double) totalitems / (double) limit);
//
//		PageRequest pageable = new PageRequest(page, limit, totalitems, totalpages);
//		List<BillSeparateHistoryDTO> bills = billSeparateService.findAllBillSeparateByAccountId(account.getId(), status,Payment,pageable.getLimit(), pageable.getOffset());
//		model.addAttribute("current_account", account);
//		model.addAttribute("bills", bills);
//		model.addAttribute("title", "Account Management");
//		model.addAttribute("pageable", pageable);
//
//		return new ModelAndView("profile/myBill/waitting-payment");
//	}
//
//	@GetMapping("/profile/mybills/deny")
//	public ModelAndView denyList(Model model,
//			@RequestParam(value = "page", required = false, defaultValue = "1") int page,
//			@RequestParam(value = "limit", required = false, defaultValue = "10") int limit) {
//		Account account = accountService.findCurrentAccount();
//		Long status = (long) 2;
//		Long Payment = (long) 1;
//		int totalitems = billSeparateService.countBillSeparateByAccountId(account.getId(), status,Payment);
//		int totalpages = (int) Math.ceil((double) totalitems / (double) limit);
//
//		PageRequest pageable = new PageRequest(page, limit, totalitems, totalpages);
//		List<BillSeparateHistoryDTO> bills = billSeparateService.findAllBillSeparateByAccountId(account.getId(), status,Payment,pageable.getLimit(), pageable.getOffset());
//		model.addAttribute("current_account", account);
//		model.addAttribute("pageable", pageable);
//
//		model.addAttribute("bills", bills);
//		model.addAttribute("title", "Account Management");
//		return new ModelAndView("profile/myBill/deny-bill-table");
//	}
//
//	@GetMapping("profile/mybills/error-payment")
//	public ModelAndView errorList(Model model,
//			@RequestParam(value = "page", required = false, defaultValue = "1") int page,
//			@RequestParam(value = "limit", required = false, defaultValue = "10") int limit) {
//		Account account = accountService.findCurrentAccount();
//		Long status = null;
//		Long Payment = (long) -1;
//		int totalitems = billSeparateService.countBillSeparateByAccountId(account.getId(), status,Payment);
//		int totalpages = (int) Math.ceil((double) totalitems / (double) limit);
//
//		PageRequest pageable = new PageRequest(page, limit, totalitems, totalpages);
//		List<BillSeparateHistoryDTO> bills = billSeparateService.findAllBillSeparateByAccountId(account.getId(), status,Payment,pageable.getLimit(), pageable.getOffset());
//		model.addAttribute("current_account", account);
//		model.addAttribute("bills", bills);
//		model.addAttribute("pageable", pageable);
//
//		model.addAttribute("title", "Account Management");
//		return new ModelAndView("profile/myBill/error-payment");
//	}
//
//	@GetMapping("profile/mybills/{bill_id}")
//	ModelAndView BillDetail(Model model, @PathVariable("bill_id") Long bill_id) {
//		Account account = accountService.findCurrentAccount();
//		List<BillItemDto> billItems = billSeparateService.findBillItemByBillSeparateId(bill_id, account.getId());
//		if(billItems.isEmpty()) {
//			return new ModelAndView("404");
//		}
//		BillSeparateHistoryDTO bill = billSeparateService.findBillSeparateById(bill_id);
//		model.addAttribute("current_account", account);
//		model.addAttribute("billItems", billItems);
//		model.addAttribute("bill", bill);
//		model.addAttribute("title", "Account Management");
//		return new ModelAndView("profile/myBill/billItem");
//	}
//}
