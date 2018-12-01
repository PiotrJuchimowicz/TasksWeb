package com.company.project.service;


import com.company.project.model.AccountEntity;
import com.company.project.model.UserEntity;

import java.util.List;

public interface AccountService extends AbstractService<AccountEntity> {
    AccountEntity findByEmail(String email);

    AccountEntity findByUser(UserEntity userEntity);

    List<AccountEntity> findAccountEntitiesByActive(Boolean isActive);
}
