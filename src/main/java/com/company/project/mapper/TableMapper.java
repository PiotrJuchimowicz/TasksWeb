package com.company.project.mapper;

import com.company.project.dto.TableDto;
import com.company.project.exception.MapperException;
import com.company.project.model.GroupEntity;
import com.company.project.model.ProjectEntity;
import com.company.project.model.TableEntity;
import com.company.project.model.UserEntity;
import com.company.project.service.GroupService;
import com.company.project.service.ProjectService;
import com.company.project.service.TableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TableMapper implements AbstractMapper<TableEntity, TableDto> {
    private ProjectService projectService;
    private GroupService groupService;

    @Autowired
    public TableMapper(ProjectService projectService,GroupService groupService){
        this.groupService = groupService;
        this.projectService = projectService;
    }

    @Override
    public void fromDtoToExistingEntity(TableDto tableDto, TableEntity tableEntity) {
        if (tableDto == null || tableEntity == null) {
            throw new MapperException("Unable to map from TableDto to existing TableEntity");
        }
        String name = tableDto.getName();
        if (name != null) {
            tableEntity.setName(name);
        }
        Long groupId = tableDto.getGroupId();
        Long projectId = tableDto.getProjectId();
        if (groupId != null && projectId == null) {
            throw new MapperException("You did not send projectId");
        } else if (groupId == null && projectId != null) {
            throw new MapperException("You did not send groupId");
        } else if (groupId != null && projectId != null) {
            ProjectEntity projectEntity = projectService.read(projectId);
            GroupEntity groupEntity = groupService.read(groupId);
            tableEntity.setProject(projectEntity);
            tableEntity.setGroup(groupEntity);
        }
    }

    @Override
    public TableEntity fromDtoToNewEntity(TableDto tableDto) {
        if(tableDto==null){
            throw new MapperException("Unable to map from TableDto to new TableEntity");
        }
        TableEntity tableEntity = new TableEntity();
        tableEntity.setName(tableDto.getName());
        Long groupId = tableDto.getGroupId();
        Long projectId = tableDto.getProjectId();
        if (groupId != null && projectId == null) {
            throw new MapperException("You did not send projectId");
        } else if (groupId == null && projectId != null) {
            throw new MapperException("You did not send groupId");
        } else if (groupId != null && projectId != null) {
            ProjectEntity projectEntity = projectService.read(projectId);
            GroupEntity groupEntity = groupService.read(groupId);
            tableEntity.setProject(projectEntity);
            tableEntity.setGroup(groupEntity);
        }
        return tableEntity;
    }

    @Override
    public TableDto fromEntityToNewDto(TableEntity tableEntity) {
        if(tableEntity==null){
            throw new MapperException("Unable to map from TableEntity to TableDto");
        }
        TableDto tableDto = new TableDto();
        tableDto.setId(tableEntity.getId());
        tableDto.setName(tableEntity.getName());
        return tableDto;
    }
}
