package vn.com.unit.repository;

import java.util.List;

import org.springframework.data.mirage.repository.MirageRepository;
import org.springframework.data.mirage.repository.query.Modifying;
import org.springframework.data.repository.query.Param;

import vn.com.unit.dto.BillItemDto;
import vn.com.unit.dto.BillSeparateHistoryDTO;
import vn.com.unit.dto.BillSeparateShopViewDto;
import vn.com.unit.entity.BillSeparate;
//import vn.com.unit.entity.BillSeparateShop;
//import vn.com.unit.entity.HistoryBillSeparate;
//import vn.com.unit.entity.billItemSeparate;

public interface BillSeparateRepository extends MirageRepository<BillSeparate, Long> {

	public List<BillSeparate> findBillSeparatePaymentSuccessAndStatusWaitingByShopId(@Param("shop_id") Long shop_id);
	
	public List<BillSeparateShopViewDto> findBillSeparateByPaymentAndStatusAndShopId(@Param("payment") Long payment,
			@Param("status") Long status,@Param("shop_id") Long shop_id,
			@Param("sizeOfPage") Integer sizeOfPage,
			@Param("offset") Integer offset);
	
	public int countBillSeparateByPaymentAndStatusAndShopId(@Param("payment") Long payment,@Param("status") Long status,@Param("shop_id") Long shop_id);
	
	@Modifying
	public void saveBillSeparateStatus(@Param("bill_separate_id") Long bill_separate_id, @Param("status") int status);
	
	public List<BillSeparateHistoryDTO> findAllBillSeparateByAccountId(
			@Param("account_id") Long account_id,
			@Param("status") Long status,
			@Param("payment") Long payment,
			@Param("sizeOfPage") Integer sizeOfPage,
			@Param("offset") Integer offset);
	
	public List<BillItemDto> findBillItemByBillSeparateId(@Param("bill_separate_id") Long bill_separate_id,@Param("account") Long account);
	
	public BillSeparateHistoryDTO findBillSeparateItemById(@Param("id") Long id);
	public int countAllBillSeparateByAccountId(
			@Param("account_id") Long account_id,
			@Param("status") Long status,
			@Param("payment") Long payment
			);
}
