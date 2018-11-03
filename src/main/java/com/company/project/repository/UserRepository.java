package com.company.project.repository;

import com.company.project.model.AccountEntity;
import com.company.project.model.RoleEntity;
import com.company.project.model.UserEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends AbstractRepository<UserEntity> {
    List<UserEntity> findAllBySurname(String surname);
    UserEntity findByAccount(AccountEntity accountEntity);
    //finds users with specific role
    @Query("SELECT userEntity  FROM RoleEntity roleEntity JOIN roleEntity.user userEntity WHERE roleEntity.roleValue=:roleValue")
    List<UserEntity> findUserEntitiesByRoles(@Param("roleValue")RoleEntity.Role roleValue);
}
