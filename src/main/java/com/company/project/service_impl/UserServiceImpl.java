package com.company.project.service_impl;


import com.company.project.model.AccountEntity;
import com.company.project.model.RoleEntity;
import com.company.project.model.UserEntity;
import com.company.project.repository.AbstractRepository;
import com.company.project.repository.UserRepository;
import com.company.project.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class UserServiceImpl extends AbstractServiceImpl<UserEntity> implements UserService {
    @Autowired
    public UserServiceImpl(AbstractRepository<UserEntity> abstractRepository) {
        super(abstractRepository);
    }

    @Override
    public UserEntity create(UserEntity object) {
        String passwordHash = String.valueOf(object.getAccount().getPassword().hashCode());
        object.getAccount().setPassword(passwordHash);
        return super.create(object);
    }

    @Override
    public UserEntity findByEmail(String email) {
        return getUserRepository().findByEmail(email);
    }

    @Override
    public List<UserEntity> findAllBySurname(String surname) {
        return getUserRepository().findAllBySurname(surname);
    }

    @Override
    public UserEntity findByAccount(AccountEntity accountEntity) {
        return getUserRepository().findByAccount(accountEntity);
    }

    @Override
    public List<UserEntity> findAllByRole(RoleEntity.Role roleValue) {
        return getUserRepository().findAllByRole(roleValue);
    }

    private UserRepository getUserRepository() {
        return (UserRepository) this.getAbstractRepository();
    }
}
