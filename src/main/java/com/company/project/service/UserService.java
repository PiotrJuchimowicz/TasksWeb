package com.company.project.service;


import com.company.project.model.AccountEntity;
import com.company.project.model.RoleEntity;
import com.company.project.model.UserEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserService extends AbstractService<UserEntity> {
    UserEntity findByEmail(String email);
    List<UserEntity> findAllBySurname(String surname);
    UserEntity findByAccount(AccountEntity accountEntity);
    //finds users with specific role
    List<UserEntity> findAllByRole( RoleEntity.Role roleValue);
}
