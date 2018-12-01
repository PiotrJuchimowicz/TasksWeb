package com.company.project.mapper;

import com.company.project.dto.TaskDto;
import com.company.project.exception.MapperException;
import com.company.project.model.TaskEntity;
import com.company.project.model.UserEntity;
import com.company.project.service.UserService;
import com.sun.org.apache.xpath.internal.operations.Bool;
import org.hibernate.proxy.HibernateProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.support.TransactionSynchronizationManager;

import java.util.Set;

@Component
public class TaskMapper implements AbstractMapper<TaskEntity, TaskDto> {
    private UserService userService;

    @Autowired
    public TaskMapper(UserService userService) {
        this.userService = userService;
    }

    @Override
    public void fromDtoToExistingEntity(TaskDto taskDto, TaskEntity taskEntity) {
        if (taskDto == null || taskEntity == null) {
            throw new MapperException("Unable to map from TaskDto to existing TaskEntity");
        }
        Long userId = taskDto.getUserId();
        if (userId != null) {
            UserEntity userEntity = userService.findUserWithTasks(userId);
            userEntity.addTask(taskEntity);
        }
        String name = taskDto.getName();
        if (name != null) {
            taskEntity.setName(name);
        }
        String description = taskDto.getDescription();
        if (description != null) {
            taskEntity.setDescription(description);
        }
        String priorityString = taskDto.getPriority();
        if (priorityString != null) {
            TaskEntity.Priority priority = TaskEntity.Priority.valueOf(priorityString);
            taskEntity.setPriority(priority);
        }
    }

    @Override
    public TaskEntity fromDtoToNewEntity(TaskDto taskDto) {
        if (taskDto == null) {
            throw new MapperException("Unable to map from TaskDto to new TaskEntity");
        }
        TaskEntity taskEntity = new TaskEntity();
        taskEntity.setDescription(taskDto.getDescription());
        taskEntity.setName(taskDto.getName());
        TaskEntity.Priority priority = TaskEntity.Priority.valueOf(taskDto.getPriority());
        taskEntity.setPriority(priority);
        UserEntity userEntity = userService.read(taskDto.getUserId());
        taskEntity.getUsers().add(userEntity);
        return taskEntity;
    }

    @Override
    public TaskDto fromEntityToNewDto(TaskEntity taskEntity) {
        if(taskEntity==null){
            throw new MapperException("Unable to map from TaskEntity to TaskDto");
        }
        TaskDto taskDto = new TaskDto();
        taskDto.setId(taskEntity.getId());
        taskDto.setName(taskEntity.getName());
        taskDto.setDescription(taskEntity.getDescription());
        taskDto.setPriority(taskEntity.getPriority().toString());
        return taskDto;
    }
}
