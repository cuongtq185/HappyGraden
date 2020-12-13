package vn.com.unit.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import vn.com.unit.dto.BillItemDto;
import vn.com.unit.entity.BillItem;
import vn.com.unit.entity.Product;
import vn.com.unit.repository.BillItemRepository;
import vn.com.unit.repository.BillRepository;
import vn.com.unit.repository.ProductRepository;
import vn.com.unit.repository.ShopRepository;
import vn.com.unit.service.BillItemService;
import vn.com.unit.service.ProductService;

@Service
@Transactional
public class BillItemServiceImpl implements BillItemService {

	@Autowired
	ShopRepository shopRepository;
	
	@Autowired
	ProductRepository productRepository;
	
	@Autowired
	ProductService productService;
	
	@Autowired
	BillRepository billRepository;
	
	@Autowired
	BillItemRepository billItemRepository;
	
	
	@Override
	public List<BillItemDto> findAllBillItemByBillSeparateId(Long bill_separate_id) {

		//List<BillItem> bill_item_list = new ArrayList<BillItem>();
		List<BillItemDto> bill_item_dto_list = new ArrayList<BillItemDto>();
//		int total = 0;
		try {
			bill_item_dto_list = billItemRepository.findAllBillItemByBillSeparateId(bill_separate_id);
			/*for (BillItem bill_item : bill_item_dto_list) {
				Long product_id = (long) bill_item.getProduct();
				Product product = productService.findProductByProductId(product_id);
				
				BillItemDto bill_item_dto = new BillItemDto(bill_item);
				
				bill_item_dto.setProductName(product.getName());*/
//				total = total + (product.getPrice() * bill_item.getQuantity());
			//}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return bill_item_dto_list;
	}
	
	@Override
	public int totalPriceOfBillByBillSeparateId(Long bill_separate_id) {

		List<BillItemDto> bill_item_dto_list = new ArrayList<BillItemDto>();
		int total = 0;
		try {
			bill_item_dto_list = billItemRepository.findAllBillItemByBillSeparateId(bill_separate_id);
			for (BillItem bill_item : bill_item_dto_list) {
				Long product_id = (long) bill_item.getProduct();
				Product product = productService.findProductByProductId(product_id);
//				bill_item.setProduct_name(product_name);
//				total = total + (product.getPrice() * bill_item.getQuantity());
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return total;
	}
	
	@Override
	public List<BillItem> findAllBillItem() {
		
		return billItemRepository.findAllBillItem();
	}

	@Override
	public List<BillItemDto> findAllBillItemByBillId(Long id) {
		return billItemRepository.findAllBillItemByBillId(id);
	}
	

}
