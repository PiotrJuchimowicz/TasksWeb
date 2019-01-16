package com.company.project.mapper;

import com.company.project.dto.TaskDto;
import com.company.project.exception.MapperException;
import com.company.project.exception.UnableToFindUserWithEmail;
import com.company.project.model.ProjectEntity;
import com.company.project.model.TaskEntity;
import com.company.project.model.UserEntity;
import com.company.project.service.ProjectService;
import com.company.project.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TaskMapper implements AbstractMapper<TaskEntity, TaskDto> {
    private UserService userService;
    private ProjectService projectService;

    @Autowired
    public TaskMapper(UserService userService, ProjectService projectService) {
        this.userService = userService;
        this.projectService = projectService;
    }

    @Override
    public void fromDtoToExistingEntity(TaskDto taskDto, TaskEntity taskEntity) {
        if (taskDto == null || taskEntity == null) {
            throw new MapperException("Unable to map from TaskDto to existing TaskEntity");
        }
        Long projectId = taskDto.getProjectId();
        if (projectId != null) {
            throw new UnsupportedOperationException("Task can not be assignet to different project");
        }
        String userEmail = taskDto.getUserEmail();
        if (taskDto.getUserEmail() != null) {
            UserEntity userEntity = userService.findByEmail(userEmail);
            if (userEntity == null) {
                throw new UnableToFindUserWithEmail("User with email: " + userEmail + " does not exist");
            }

            Long userId = userEntity.getId();
            if (userId != null) {
                userEntity = userService.findUserWithTasks(userId);
                userEntity.addTask(taskEntity);
            }
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
        Boolean isDone = taskDto.getIsDone();
        if (isDone != null) {
            taskEntity.setIsDone(isDone);
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
        UserEntity userEntity = userService.findByEmail(taskDto.getUserEmail());
        if (userEntity == null) {
            throw new UnableToFindUserWithEmail("Unable to find user with emaiL: " + taskDto.getUserEmail());
        }
        taskEntity.getUsers().add(userEntity);
        ProjectEntity projectEntity = projectService.read(taskDto.getProjectId());
        taskEntity.setProjectEntity(projectEntity);
        taskEntity.setIsDone(taskDto.getIsDone());
        return taskEntity;
    }

    @Override
    public TaskDto fromEntityToNewDto(TaskEntity taskEntity) {
        if (taskEntity == null) {
            throw new MapperException("Unable to map from TaskEntity to TaskDto");
        }
        TaskDto taskDto = new TaskDto();
        taskDto.setId(taskEntity.getId());
        taskDto.setName(taskEntity.getName());
        taskDto.setDescription(taskEntity.getDescription());
        taskDto.setPriority(taskEntity.getPriority().toString());
        taskDto.setProjectId(taskEntity.getProjectEntity().getId());
        taskDto.setIsDone(taskEntity.getIsDone());
        return taskDto;
    }
}
