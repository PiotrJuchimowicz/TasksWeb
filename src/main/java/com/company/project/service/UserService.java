package com.company.project.service;


import com.company.project.model.AccountEntity;
import com.company.project.model.ProjectEntity;
import com.company.project.model.RoleEntity;
import com.company.project.model.UserEntity;

import java.util.List;

public interface UserService extends AbstractService<UserEntity> {
    UserEntity findByEmail(String email);

    List<UserEntity> findAllBySurname(String surname);

    UserEntity findByAccount(AccountEntity accountEntity);

    List<UserEntity> findAllByRole(RoleEntity.Role roleValue);

    UserEntity findUserWithTasks(Long userId);

    UserEntity findUserWithManagedProjects(Long userId);

    List<ProjectEntity> getProjectsInWhichHeParticipates(Long id);
}
