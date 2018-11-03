package com.company.project.service_impl;

import com.company.project.model.TaskEntity;
import com.company.project.repository.AbstractRepository;
import com.company.project.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TaskServiceImpl extends AbstractServiceImpl<TaskEntity> implements TaskService {
    @Autowired
    public TaskServiceImpl(AbstractRepository<TaskEntity> abstractRepository) {
        super(abstractRepository);
    }
}
