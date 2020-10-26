package vn.com.unit.repository;

import java.util.List;

import org.springframework.data.mirage.repository.MirageRepository;
import org.springframework.data.mirage.repository.query.Modifying;
import org.springframework.data.repository.query.Param;

import vn.com.unit.dto.ShopDto;
import vn.com.unit.entity.Shop;

public interface ShopRepository extends MirageRepository<Shop, Long> {

	public Shop findShopByAccountId(@Param("account_id") Long account_id);
	
	
	@Modifying
	public void saveShop(@Param("shop_id") Long shop_id, @Param("name") String name , @Param("email") String email, @Param("phone") String phone,@Param("address") String address,@Param("detail") String detail);

	@Modifying
	public void createShop(@Param("account_id") Long account_id ,@Param("name") String name , @Param("email") String email, @Param("phone") String phone,@Param("address") String address,@Param("detail") String detail,@Param("status") int status);
	
	public List<ShopDto> findAllShop(@Param("sizeOfPage") Integer sizeOfPage,@Param("offset") Integer offset);

	@Modifying
	public void setDisableShop(@Param("shop_id") Long shop_id,@Param("status") Long status );
	
	@Modifying
	public void setActivateShop(@Param("shop_id") Long shop_id,@Param("status") int status );
	
	public int countAllShop();
	public int countShopByStatus(@Param("status") int status);
	public List<Shop> searchAllShop();
	
	public List<ShopDto> findShopByStatus(@Param("sizeOfPage") Integer sizeOfPage,@Param("offset") Integer offset,@Param("status") Integer status);

}
