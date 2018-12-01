package com.company.project.service;


import com.company.project.model.RoleEntity;
import com.company.project.model.UserEntity;

import java.util.List;

public interface RoleService extends AbstractService<RoleEntity> {
    List<RoleEntity> findRoleEntitiesByUser(UserEntity userEntity);
}
