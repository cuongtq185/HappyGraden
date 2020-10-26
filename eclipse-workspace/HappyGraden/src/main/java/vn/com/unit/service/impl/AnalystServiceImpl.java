package vn.com.unit.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import vn.com.unit.repository.RevenueRepository;
import vn.com.unit.service.AnalystService;

@Service
@Transactional
public class AnalystServiceImpl implements AnalystService {

	@Autowired
	RevenueRepository revenueRepository;
	
	@Override
	public Long caculateShopRevenueLastDayByShopId(Long shop_id) {
		return revenueRepository.caculateShopRevenueLastDayByShopId(shop_id);
	}

	@Override
	public Long caculateBillPaymentErrorLastDayByShopId(Long shop_id) {
		return revenueRepository.caculateBillPaymentErrorLastDayByShopId(shop_id);
	}

}
