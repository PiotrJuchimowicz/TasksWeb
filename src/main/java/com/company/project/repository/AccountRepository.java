package com.company.project.repository;

import com.company.project.model.AccountEntity;
import com.company.project.model.UserEntity;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccountRepository  extends AbstractRepository<AccountEntity> {
    AccountEntity findByEmail(String email);
    AccountEntity findByUser(UserEntity userEntity);
    List<AccountEntity> findAccountEntitiesByIsActive(Boolean isActive);
}
