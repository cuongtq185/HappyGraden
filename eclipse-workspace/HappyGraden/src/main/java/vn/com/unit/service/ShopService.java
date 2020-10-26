package vn.com.unit.service;

import java.util.List;

import vn.com.unit.dto.ShopCreateDto;
import vn.com.unit.dto.ShopDto;
import vn.com.unit.entity.Shop;

public interface ShopService {
	
	public Shop findShopByAccountId(Long account_id);
	
	public void saveShop(Long shop_id, String name, String email, String phone, String address, String detail);
	
	public boolean setActivateShop(Long shop_id, int status);
	
	public void createShop(Long account_id,String name, String email, String phone, String address, String detail, int status);
	
	public boolean setDisableShop(Long account_id, int status);
	
	public List<ShopDto> findAllShop(int limit,int offset);
	
	public int countAllShop();
	public List<ShopDto> findShopByStatus(int limit,int offset,int status);
	
	public List<Shop> searchAllShop();
	
	public int countShopByStatus(int status);

	public Shop save(ShopCreateDto shop_create_dto);
}
