package com.company.project.service_impl;


import com.company.project.model.AccountEntity;
import com.company.project.model.UserEntity;
import com.company.project.repository.AbstractRepository;
import com.company.project.repository.AccountRepository;
import com.company.project.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class AccountServiceImpl extends AbstractServiceImpl<AccountEntity> implements AccountService {
    @Autowired
    public AccountServiceImpl(AbstractRepository<AccountEntity> abstractRepository) {
        super(abstractRepository);
    }

    @Override
    public AccountEntity create(AccountEntity object) {
        int passwordHash = object.getPassword().hashCode();
        object.setPassword(String.valueOf(passwordHash));
        return super.create(object);
    }

    @Override
    public AccountEntity findByEmail(String email) {
        return getAccountRepository().findByEmail(email);
    }

    @Override
    public AccountEntity findByUser(UserEntity userEntity) {
        return getAccountRepository().findByUser(userEntity);
    }

    @Override
    public List<AccountEntity> findAccountEntitiesByActive(Boolean isActive) {
        return getAccountRepository().findAccountEntitiesByIsActive(isActive);
    }

    private AccountRepository getAccountRepository() {
        return (AccountRepository) this.getAbstractRepository();
    }
}
