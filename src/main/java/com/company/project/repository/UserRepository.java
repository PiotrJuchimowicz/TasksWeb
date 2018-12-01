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
    @Query("SELECT userEntity FROM UserEntity userEntity JOIN FETCH userEntity.account accountEntity WHERE accountEntity.email=:email")
    UserEntity findByEmail(@Param("email") String email);

    List<UserEntity> findAllBySurname(String surname);

    UserEntity findByAccount(AccountEntity accountEntity);

    //finds users with specific role
    @Query("SELECT userEntity  FROM RoleEntity roleEntity JOIN roleEntity.user userEntity WHERE roleEntity.roleValue=:roleValue")
    List<UserEntity> findAllByRole(@Param("roleValue") RoleEntity.Role roleValue);
}
