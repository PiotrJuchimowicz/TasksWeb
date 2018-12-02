package com.company.project.mapper;

import com.company.project.dto.ProjectDto;
import com.company.project.exception.MapperException;
import com.company.project.model.ProjectEntity;
import com.company.project.model.UserEntity;
import com.company.project.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ProjectMapper implements AbstractMapper<ProjectEntity, ProjectDto> {
    private UserService  userService;

    @Autowired
    public ProjectMapper(UserService userService){
        this.userService = userService;
    }
    @Override
    public void fromDtoToExistingEntity(ProjectDto projectDto, ProjectEntity projectEntity) {
        if(projectDto==null || projectEntity== null){
            throw new MapperException("Unable to map from ProjectDto to existing ProjectEntity");
        }
        String name = projectDto.getName();
        if(name!=null){
            projectEntity.setName(name);
        }
        String description = projectDto.getDescription();
        if(description!=null){
            projectEntity.setDescription(description);
        }
        Long ownerId = projectDto.getOwnerId();
        if(ownerId!=null){
            UserEntity newProjectOwner = userService.findUserWithManagedProjects(ownerId);
            newProjectOwner.addToManagedProjects(projectEntity);
        }

    }

    @Override
    public ProjectEntity fromDtoToNewEntity(ProjectDto projectDto) {
       if(projectDto==null){
           throw new MapperException("Unable to map from ProjectDto to new ProjectEntity");
       }
       ProjectEntity projectEntity = new ProjectEntity();
       UserEntity projectOwner = userService.findUserWithManagedProjects(projectDto.getOwnerId());
       projectOwner.addToManagedProjects(projectEntity);
       projectEntity.setName(projectDto.getName());
       projectEntity.setDescription(projectDto.getDescription());
       return projectEntity;
    }

    @Override
    public ProjectDto fromEntityToNewDto(ProjectEntity projectEntity) {
        if(projectEntity==null){
            throw new MapperException("Unable to map from ProjectEntity to ProjectDto");
        }
        ProjectDto projectDto = new ProjectDto();
        projectDto.setId(projectEntity.getId());
        projectDto.setOwnerId(projectEntity.getOwner().getId());
        projectDto.setName(projectEntity.getName());
        projectDto.setDescription(projectEntity.getDescription());
        return projectDto;
    }
}
