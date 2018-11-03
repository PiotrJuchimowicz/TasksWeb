package com.company.project.service_impl;


import com.company.project.model.AccountEntity;
import com.company.project.repository.AbstractRepository;
import com.company.project.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountServiceImpl extends AbstractServiceImpl<AccountEntity> implements AccountService {
    @Autowired
    public AccountServiceImpl(AbstractRepository<AccountEntity> abstractRepository){
        super(abstractRepository);
    }
}
