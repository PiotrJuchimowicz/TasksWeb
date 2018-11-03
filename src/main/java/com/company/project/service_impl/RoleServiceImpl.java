package com.company.project.service_impl;


import com.company.project.model.RoleEntity;
import com.company.project.repository.AbstractRepository;
import com.company.project.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl extends AbstractServiceImpl<RoleEntity> implements RoleService {
    @Autowired
    public RoleServiceImpl(AbstractRepository<RoleEntity> abstractRepository){
        super(abstractRepository);
    }
}
