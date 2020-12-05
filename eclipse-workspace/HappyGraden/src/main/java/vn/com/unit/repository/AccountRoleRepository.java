package vn.com.unit.repository;

import org.springframework.data.mirage.repository.MirageRepository;

import vn.com.unit.entity.Account;

public interface AccountRoleRepository extends MirageRepository<Account, Long> {

}
