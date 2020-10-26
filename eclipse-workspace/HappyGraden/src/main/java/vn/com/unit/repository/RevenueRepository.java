package vn.com.unit.repository;

import org.springframework.data.mirage.repository.MirageRepository;
import org.springframework.data.repository.query.Param;

//import vn.com.unit.entity.Revenue;

//Revenue
public interface RevenueRepository extends MirageRepository<Object, Long> {

	public Long caculateShopRevenueLastDayByShopId(@Param("shop_id") Long shop_id);

	public Long caculateBillPaymentErrorLastDayByShopId(@Param("shop_id") Long shop_id);

}
