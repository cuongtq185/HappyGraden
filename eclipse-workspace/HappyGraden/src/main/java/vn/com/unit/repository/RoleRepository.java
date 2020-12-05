package vn.com.unit.repository;

import java.util.List;

import org.springframework.data.mirage.repository.MirageRepository;
import org.springframework.data.repository.query.Param;

import vn.com.unit.entity.Role;

public interface RoleRepository extends MirageRepository<Role, Long> {

	// tìm quyền bởi id
	public List<Role> findRoleByAccountId(@Param("account_id") Long account_id);
	
	// tìm quyền bởi tên quyền
	public Long findRoleIdByName(@Param("role_name") String role_name);
	
}
