package com.company.project.controller;

import com.company.project.dto.GroupDto;
import com.company.project.dto.ProjectDto;
import com.company.project.dto.TaskDto;
import com.company.project.mapper.AbstractMapper;
import com.company.project.model.GroupEntity;
import com.company.project.model.ProjectEntity;
import com.company.project.model.TaskEntity;
import com.company.project.service.AbstractService;
import com.company.project.service.ProjectService;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.security.acl.Group;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/projects")
@CrossOrigin(origins = "http://localhost:4200")
public class ProjectController extends AbstractController<ProjectEntity, ProjectDto> {
    private AbstractMapper<TaskEntity, TaskDto> taskMapper;
    private AbstractMapper<GroupEntity,GroupDto> groupMapper;
    @Autowired
    public ProjectController(AbstractMapper<ProjectEntity, ProjectDto> abstractMapper,
                             AbstractService<ProjectEntity> abstractService,AbstractMapper<TaskEntity,TaskDto> taskMapper,
                             AbstractMapper<GroupEntity,GroupDto> groupMapper) {
        super(abstractMapper, abstractService, LoggerFactory.getLogger(ProjectController.class));
        this.taskMapper = taskMapper;
        this.groupMapper = groupMapper;
    }

    @GetMapping("/withTasks/{projectId}")
    public ProjectDto getProjectWithAssignedTasks(@PathVariable("projectId") Long projectId) {
        ProjectEntity projectEntity = this.getProjectService().findProjectWithTasks(projectId);
        ProjectDto projectDto = this.getAbstractMapper().fromEntityToNewDto(projectEntity);
        Set<TaskEntity> taskEntities = projectEntity.getTasks();
        List<TaskDto> taskDtos = new LinkedList<>();
        for(TaskEntity taskEntity : taskEntities){
            TaskDto taskDto = taskMapper.fromEntityToNewDto(taskEntity);
            taskDtos.add(taskDto);
        }
        projectDto.setTasks(taskDtos);
        return projectDto;
    }

    @GetMapping("/groups/{projectId}")
    public List<GroupDto> getGroupsConnectedWithProject(@PathVariable("projectId") Long projectId){
        List<GroupEntity> groupEntities = this.getProjectService().getGroupsConnectedWithProject(projectId);
        List<GroupDto> groupDtos = new LinkedList<>();
        for(GroupEntity groupEntity : groupEntities){
            GroupDto groupDto = this.groupMapper.fromEntityToNewDto(groupEntity);
            groupDtos.add(groupDto);
        }
        return groupDtos;
    }

    private ProjectService getProjectService() {
        return (ProjectService) this.getAbstractService();
    }
}
