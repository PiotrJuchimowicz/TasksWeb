package com.company.project.service_impl;


import com.company.project.model.RoleEntity;
import com.company.project.model.UserEntity;
import com.company.project.repository.AbstractRepository;
import com.company.project.repository.RoleRepository;
import com.company.project.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class RoleServiceImpl extends AbstractServiceImpl<RoleEntity> implements RoleService {
    @Autowired
    public RoleServiceImpl(AbstractRepository<RoleEntity> abstractRepository) {
        super(abstractRepository);
    }

    @Override
    public List<RoleEntity> findRoleEntitiesByUser(UserEntity userEntity) {
        return getRoleRepository().findRoleEntitiesByUser(userEntity);
    }

    private RoleRepository getRoleRepository() {
        return (RoleRepository) this.getAbstractRepository();
    }
}
