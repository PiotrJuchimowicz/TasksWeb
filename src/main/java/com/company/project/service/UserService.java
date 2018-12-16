package com.company.project.service;


import com.company.project.dto.ProjectDto;
import com.company.project.model.AccountEntity;
import com.company.project.model.RoleEntity;
import com.company.project.model.TaskEntity;
import com.company.project.model.UserEntity;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Set;

public interface UserService extends AbstractService<UserEntity> {
    UserEntity findByEmail(String email);

    List<UserEntity> findAllBySurname(String surname);

    UserEntity findByAccount(AccountEntity accountEntity);

    List<UserEntity> findAllByRole(RoleEntity.Role roleValue);

    UserEntity findUserWithTasks(Long userId);

    UserEntity findUserWithManagedProjects(Long userId);

    List<ProjectDto> getProjectsInWhichHeParticipates(Long id);

}
