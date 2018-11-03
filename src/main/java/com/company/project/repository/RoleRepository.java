package com.company.project.repository;

import com.company.project.model.RoleEntity;
import com.company.project.model.UserEntity;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoleRepository extends AbstractRepository<RoleEntity> {
    List<RoleEntity> findRoleEntitiesByUser(UserEntity userEntity);
}
