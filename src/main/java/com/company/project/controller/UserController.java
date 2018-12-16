package com.company.project.controller;

import com.company.project.dto.ProjectDto;
import com.company.project.dto.TaskDto;
import com.company.project.dto.UserDto;
import com.company.project.mapper.AbstractMapper;
import com.company.project.model.ProjectEntity;
import com.company.project.model.TaskEntity;
import com.company.project.model.UserEntity;
import com.company.project.service.AbstractService;
import com.company.project.service.UserService;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/users")
@CrossOrigin(origins = "http://localhost:4200")
public class UserController extends AbstractController<UserEntity, UserDto> {
    private AbstractMapper<TaskEntity, TaskDto> taskMapper;
    private AbstractMapper<ProjectEntity, ProjectDto> projectMapper;

    @Autowired
    public UserController(AbstractMapper<UserEntity, UserDto> abstractMapper,
                          AbstractService<UserEntity> abstractService,AbstractMapper<TaskEntity,TaskDto> taskMapper,
                          AbstractMapper<ProjectEntity,ProjectDto> projectMapper) {
        super(abstractMapper, abstractService, LoggerFactory.getLogger(UserController.class));
        this.taskMapper = taskMapper;
        this.projectMapper = projectMapper;
    }

    @GetMapping("/withTasks/{userId}")
    public UserDto getUserWithTasks(@PathVariable("userId") Long userId){
        UserEntity userEntity = this.getUserService().findUserWithTasks(userId);
        Set<TaskEntity> taskEntities = userEntity.getTasks();
        UserDto userDto = this.getAbstractMapper().fromEntityToNewDto(userEntity);
        Set<TaskDto> taskDtos = new LinkedHashSet<>();
        for(TaskEntity taskEntity : taskEntities){
            TaskDto taskDto = taskMapper.fromEntityToNewDto(taskEntity);
            taskDtos.add(taskDto);
        }
        userDto.setTasks(taskDtos);
        userDto.setRoles(null);
        userDto.setAccount(null);
        return userDto;
    }

    @GetMapping("/withProjectsHeParticipates/{id}")
    public List<ProjectDto> getProjectsInWhichHeParticipates(@PathVariable("id")Long id){
        return  getUserService().getProjectsInWhichHeParticipates(id);
    }

    @GetMapping("/withManagedProjects/{userId}")
    public UserDto getUserWithManagingProjects(@PathVariable("userId") Long userId){
        UserEntity userEntity = this.getUserService().findUserWithManagedProjects(userId);
        UserDto userDto = this.getAbstractMapper().fromEntityToNewDto(userEntity);
        userDto.setAccount(null);
        userDto.setRoles(null);
        Set<ProjectEntity> projectEntities = userEntity.getManagedProjects();
        Set<ProjectDto> projectDtos = new LinkedHashSet<>();
        for(ProjectEntity projectEntity : projectEntities){
            ProjectDto projectDto = this.projectMapper.fromEntityToNewDto(projectEntity);
            projectDtos.add(projectDto);
        }
        userDto.setManagedProjects(projectDtos);
        return userDto;
    }

    private UserService getUserService() {
        return (UserService) this.getAbstractService();
    }
}
