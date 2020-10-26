package vn.com.unit.repository;

import java.util.List;

import org.springframework.data.mirage.repository.MirageRepository;
import org.springframework.data.mirage.repository.query.Modifying;
import org.springframework.data.repository.query.Param;

import vn.com.unit.entity.Bill;

public interface BillRepository extends MirageRepository<Bill, Long> {

	public List<Bill> findAllBillByAccountId(@Param("account_id") Long account_id);
	
	public Bill findBillByBillId(@Param("bill_id") Long bill_id);

	public Long createBill(@Param("account_id") Long account_id, @Param("address") String address);

	@Modifying
	public void addBillItemFromCart(@Param("bill_id") Long bill_id, @Param("account_id") Long account_id);

	public Long calculateBillTotal(@Param("bill_id") Long bill_id);

	@Modifying
	public void saveBillPaymentStatus(@Param("bill_id") Long bill_id, @Param("payment_status") int payment_status);

	public Bill findBillByBillIdAndAccountId(@Param("bill_id") Long bill_id, @Param("account_id") Long account_id);
	
	public Bill test(@Param("bill_id") Long bill_id);
}
