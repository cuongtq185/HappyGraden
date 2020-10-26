package vn.com.unit.repository;

import java.util.List;

import org.springframework.data.mirage.repository.MirageRepository;
import org.springframework.data.mirage.repository.query.Modifying;
import org.springframework.data.repository.query.Param;

import vn.com.unit.dto.CartDto;
import vn.com.unit.entity.Cart;

public interface CartRepository extends MirageRepository<Cart, Long> {

	public List<CartDto> findAllCartItemByAccountId(@Param("account_id") Long account_id);
	
	public int countAllCartItemByAccountId(@Param("account_id") Long account_id);

	public Long calculateCartTotalByAccountId(@Param("account_id") Long account_id);

	@Modifying
	public void addCartItemCurrentAccount(@Param("account_id") Long account_id, @Param("product_id") Long product_id,
			@Param("quantity") int quantity);
	
	@Modifying
	public void deleteCartItemCurrentAccount(@Param("account_id") Long account_id, @Param("product_id") Long product_id);

	public Integer findProductQuantityInCart(@Param("account_id") Long account_id, @Param("product_id") Long product_id);

}
