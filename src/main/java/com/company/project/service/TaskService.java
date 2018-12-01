package com.company.project.service;


import com.company.project.model.TaskEntity;
import com.company.project.model.UserEntity;

import java.util.List;
import java.util.Set;

public interface TaskService extends AbstractService<TaskEntity> {

    TaskEntity findTaskWithUsers(Long taskId);
    void removeTaskFromUser(Long userId,Long taskId);
}

