package com.company.project.service_impl;


import com.company.project.model.GroupEntity;
import com.company.project.repository.AbstractRepository;
import com.company.project.service.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GropuServiceImpl extends AbstractServiceImpl<GroupEntity> implements GroupService {
    @Autowired
    public GropuServiceImpl(AbstractRepository<GroupEntity> abstractRepository) {
        super(abstractRepository);
    }
}
