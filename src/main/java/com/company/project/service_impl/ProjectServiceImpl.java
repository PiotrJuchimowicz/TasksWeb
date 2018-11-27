package com.company.project.service_impl;


import com.company.project.model.ProjectEntity;
import com.company.project.repository.AbstractRepository;
import com.company.project.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ProjectServiceImpl extends AbstractServiceImpl<ProjectEntity> implements ProjectService {
    @Autowired
    public ProjectServiceImpl(AbstractRepository<ProjectEntity> abstractRepository) {
        super(abstractRepository);
    }
}
