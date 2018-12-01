package com.company.project.controller;

import com.company.project.dto.TaskDto;
import com.company.project.dto.UserDto;
import com.company.project.exception.UnableToFindObjectException;
import com.company.project.mapper.AbstractMapper;
import com.company.project.model.TaskEntity;
import com.company.project.model.UserEntity;
import com.company.project.service.AbstractService;
import com.company.project.service.TaskService;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedHashSet;
import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping("/tasks")
@CrossOrigin(origins = "http://localhost:4200")
public class TaskController extends AbstractController<TaskEntity, TaskDto> {
    private AbstractMapper<UserEntity, UserDto> userMapper;

    @Autowired
    public TaskController(AbstractMapper<TaskEntity, TaskDto> abstractMapper,
                          AbstractService<TaskEntity> abstractService, AbstractMapper<UserEntity, UserDto> userMapper) {
        super(abstractMapper, abstractService, LoggerFactory.getLogger(TaskController.class));
        this.userMapper = userMapper;
    }

    @GetMapping("/withUsers/{taskId}")
    public TaskDto getTaskWithUsers(@PathVariable("taskId") Long taskId) {
        TaskEntity taskEntity = this.getTaskService().findTaskWithUsers(taskId);
        Set<UserEntity> userEntities = taskEntity.getUsers();
        TaskDto taskDto = this.getAbstractMapper().fromEntityToNewDto(taskEntity);
        Set<UserDto> userDtos = new LinkedHashSet<>();
        for (UserEntity userEntity : userEntities) {
            UserDto userDto = userMapper.fromEntityToNewDto(userEntity);
            userDtos.add(userDto);
        }
        taskDto.setUsers(userDtos);
        return taskDto;
    }

    @DeleteMapping("/removeFromUser")
    public void removeTaskFromUser(@RequestParam("userId") Long userId,
                                   @RequestParam("taskId") Long taskId) {
        this.getLogger().info("Removing task with id: " + taskId + " from user with id: " + userId);
        this.getTaskService().removeTaskFromUser(userId,taskId);

    }

    private TaskService getTaskService() {
        return (TaskService) this.getAbstractService();
    }
}
