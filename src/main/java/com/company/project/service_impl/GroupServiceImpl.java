package com.company.project.service_impl;


import com.company.project.model.GroupEntity;
import com.company.project.repository.AbstractRepository;
import com.company.project.service.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class GroupServiceImpl extends AbstractServiceImpl<GroupEntity> implements GroupService {
    @Autowired
    public GroupServiceImpl(AbstractRepository<GroupEntity> abstractRepository) {
        super(abstractRepository);
    }
}
