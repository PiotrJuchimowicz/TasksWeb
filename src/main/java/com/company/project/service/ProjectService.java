package com.company.project.service;


import com.company.project.model.ProjectEntity;

public interface ProjectService extends AbstractService<ProjectEntity> {
    ProjectEntity findProjectWithTasks(Long projectId);
}
