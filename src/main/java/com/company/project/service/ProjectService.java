package com.company.project.service;


import com.company.project.model.GroupEntity;
import com.company.project.model.ProjectEntity;

import java.util.List;

public interface ProjectService extends AbstractService<ProjectEntity> {
    ProjectEntity findProjectWithTasks(Long projectId);
    List<GroupEntity> getGroupsConnectedWithProject(Long projectId);

}
