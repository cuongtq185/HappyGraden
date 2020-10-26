package vn.com.unit.service;

import java.util.List;

import vn.com.unit.dto.BillItemDto;
import vn.com.unit.dto.BillSeparateHistoryDTO;
import vn.com.unit.dto.BillSeparateShopViewDto;
import vn.com.unit.entity.BillSeparate;
//import vn.com.unit.entity.BillSeparateShop;
//import vn.com.unit.entity.HistoryBillSeparate;
//import vn.com.unit.entity.billItemSeparate;

public interface BillSeparateService {

	public List<BillSeparate> findBillSeparatePaymentSuccessAndStatusWaitingByShopId(Long shop_id);
	
	public List<BillSeparateShopViewDto> findBillSeparateByPaymentAndStatusAndShopId(Long payment,Long status,Long shop_id,int limit,int offset);
	
	public int countBillSeparateByPaymentAndStatusAndShopId(Long payment,Long status,Long shop_id);
	
	public BillSeparate saveBillSeparateStatus(Long bill_separate_id, int status);
	
	public List<BillSeparateHistoryDTO> findAllBillSeparateByAccountId(Long account_id,Long status, Long payment,int limit,int offset);
	
	public List<BillItemDto> findBillItemByBillSeparateId(Long bill_separate_id,Long account);
	
	public BillSeparateHistoryDTO findBillSeparateById(Long id);
	
	public int countBillSeparateByAccountId(Long account_id,Long status, Long payment);
}