package com.company.project.controller;

import com.company.project.dto.TaskDto;
import com.company.project.dto.UserDto;
import com.company.project.mapper.AbstractMapper;
import com.company.project.model.TaskEntity;
import com.company.project.model.UserEntity;
import com.company.project.service.AbstractService;
import com.company.project.service.UserService;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedHashSet;
import java.util.Set;

@RestController
@RequestMapping("/users")
@CrossOrigin(origins = "http://localhost:4200")
public class UserController extends AbstractController<UserEntity, UserDto> {
    private AbstractMapper<TaskEntity, TaskDto> taskMapper;
    @Autowired
    public UserController(AbstractMapper<UserEntity, UserDto> abstractMapper,
                          AbstractService<UserEntity> abstractService,AbstractMapper<TaskEntity,TaskDto> taskMapper) {
        super(abstractMapper, abstractService, LoggerFactory.getLogger(UserController.class));
        this.taskMapper = taskMapper;
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
        return userDto;
    }

    private UserService getUserService() {
        return (UserService) this.getAbstractService();
    }
}
