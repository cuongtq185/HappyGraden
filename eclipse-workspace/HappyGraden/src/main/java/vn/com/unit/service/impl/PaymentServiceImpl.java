//package vn.com.unit.service.impl;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//
//import vn.com.unit.entity.Account;
//import vn.com.unit.service.AccountService;
//import vn.com.unit.service.BillService;
//import vn.com.unit.service.PaymentService;
//
//@Service
//@Transactional
//public class PaymentServiceImpl implements PaymentService {
//
//	@Autowired
//	AccountService accountService;
//
//	// Order
//	@Autowired
//	BillService billService;
//
//	// Get current account id
//	// -- Check cart to create bill
//	// Create bill (account id, address)
//	// Move all product in cart to bill item
//	@Override
//	public Long createBill(String address) {
//
//		Account current_account = accountService.findCurrentAccount();
//
//		Long current_account_id = current_account.getAccountId();
//		
//		// Need check cart before create bill
//
//		Long bill_id = billService.createBill(current_account_id, address);
//
//		billService.addBillItemFromCart(bill_id, current_account_id);
//
//		return bill_id;
//	}
//
//	@Override
//	public Long calculateBillTotal(Long bill_id) {
//
//		return billService.calculateBillTotal(bill_id);
//	}
//
//	@Override
//	public void savePaymentSuccess(Long bill_id) {
//		// TODO Auto-generated method stub
//		billService.saveBillPaymentStatus(bill_id, 1);
//	}
//
//	@Override
//	public void savePaymentError(Long bill_id) {
//		// TODO Auto-generated method stub
//		billService.saveBillPaymentStatus(bill_id, -1);
//	}
//
//	
//	
//}
