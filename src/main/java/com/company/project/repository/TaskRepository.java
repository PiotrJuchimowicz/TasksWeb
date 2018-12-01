package com.company.project.repository;

import com.company.project.model.TaskEntity;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskRepository extends AbstractRepository<TaskEntity> {
}
