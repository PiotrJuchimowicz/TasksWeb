package com.company.project.mapper;

import com.company.project.dto.ProjectDto;
import com.company.project.model.ProjectEntity;

public class ProjectMapper implements AbstractMapper<ProjectEntity, ProjectDto> {
    @Override
    public void fromDtoToExistingEntity(ProjectDto dto, ProjectEntity entity) {

    }

    @Override
    public ProjectEntity fromDtoToNewEntity(ProjectDto dto) {
        return null;
    }

    @Override
    public ProjectDto fromEntityToNewDto(ProjectEntity entity) {
        return null;
    }
}
