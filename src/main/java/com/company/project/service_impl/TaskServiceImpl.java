package com.company.project.service_impl;

import com.company.project.model.TaskEntity;
import com.company.project.model.UserEntity;
import com.company.project.repository.AbstractRepository;
import com.company.project.service.TaskService;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;

@Service
@Transactional
public class TaskServiceImpl extends AbstractServiceImpl<TaskEntity> implements TaskService {
    @Autowired
    public TaskServiceImpl(AbstractRepository<TaskEntity> abstractRepository) {
        super(abstractRepository);
    }


    @Override
    public TaskEntity findTaskWithUsers(Long taskId) {
        TaskEntity taskEntity = this.read(taskId);
        Hibernate.initialize(taskEntity.getUsers());
        return taskEntity;
    }
}
