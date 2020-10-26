package vn.com.unit.repository;

import java.util.List;

import org.springframework.data.mirage.repository.MirageRepository;
import org.springframework.data.repository.query.Param;

import vn.com.unit.dto.BillItemDto;
import vn.com.unit.entity.BillItem;
import vn.com.unit.entity.Shop;

public interface BillItemRepository extends MirageRepository<Shop, Long> {

	public List<BillItemDto> findAllBillItemByBillSeparateId(@Param("bill_separate_id") Long bill_separate_id);
	
	public List<BillItem> findAllBillItem();

	public List<BillItemDto> findAllBillItemByBillId(@Param("bill_id") Long bill_id);

}
