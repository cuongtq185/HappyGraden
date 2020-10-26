package vn.com.unit.service;

import java.util.List;

import vn.com.unit.dto.CartDto;

public interface CartService {

	public List<CartDto> findAllCartItemByCurrentAccount();
	
	public int countAllCartItemByCurrentAccount(Long account_id);
	
	public List<CartDto> findAllCartItemByAccountId(Long account_id);

	public Long calculateCartTotalByCurrentAccount();

	public void addCartItemCurrentAccount(Long product_id, int i);
	
	public void deleteCartItemCurrentAccount(Long product_id, Long account_id);
	
//	public void deleteCartItemCurrentAccountByProductId(Long product_id);
}
