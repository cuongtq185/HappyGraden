package vn.com.unit.service;

import java.util.List;

import vn.com.unit.dto.BillItemDto;
import vn.com.unit.entity.BillItem;

public interface BillItemService {
	
	public List<BillItemDto> findAllBillItemByBillSeparateId(Long bill_separate_id);
	
	public int totalPriceOfBillByBillSeparateId(Long bill_separate_id);
	
	public List<BillItem> findAllBillItem();

	public List<BillItemDto> findAllBillItemByBillId(Long id);

}
