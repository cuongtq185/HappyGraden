package vn.com.unit.repository;

import java.util.List;

import org.springframework.data.mirage.repository.MirageRepository;
import org.springframework.data.repository.query.Param;

import vn.com.unit.entity.Role;

public interface RoleRepository extends MirageRepository<Role, Long> {

	public List<Role> findRoleByAccountId(@Param("account_id") Long account_id);
	
	public Long findRoleIdByName(@Param("role_name") String role_name);
	
}
