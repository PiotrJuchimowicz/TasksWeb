package com.company.project.service_impl;


import com.company.project.model.*;
import com.company.project.repository.AbstractRepository;
import com.company.project.repository.UserRepository;
import com.company.project.service.UserService;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Set;

@Service
@Transactional
public class UserServiceImpl extends AbstractServiceImpl<UserEntity> implements UserService {

    @Autowired
    public UserServiceImpl(AbstractRepository<UserEntity> abstractRepository) {
        super(abstractRepository);
    }

    @Override
    public UserEntity create(UserEntity object) {
        String passwordHash = String.valueOf(object.getAccount().getPassword().hashCode());
        object.getAccount().setPassword(passwordHash);
        return super.create(object);
    }

    @Override
    public UserEntity findByEmail(String email) {
        return getUserRepository().findByEmail(email);
    }

    @Override
    public List<UserEntity> findAllBySurname(String surname) {
        return getUserRepository().findAllBySurname(surname);
    }

    @Override
    public UserEntity findByAccount(AccountEntity accountEntity) {
        return getUserRepository().findByAccount(accountEntity);
    }

    @Override
    public List<UserEntity> findAllByRole(RoleEntity.Role roleValue) {
        return getUserRepository().findAllByRole(roleValue);
    }

    @Override
    public UserEntity findUserWithTasks(Long userId) {
        UserEntity userEntity = this.read(userId);
        Hibernate.initialize(userEntity.getTasks());
        return userEntity;
    }

    @Override
    public UserEntity findUserWithManagedProjects(Long userId) {
        UserEntity userEntity = this.read(userId);
        Hibernate.initialize(userEntity.getManagedProjects());
        return userEntity;
    }

    @Override
    public List<ProjectEntity> getProjectsInWhichHeParticipates(Long id) {
        UserEntity userEntity = this.read(id);
        GroupEntity groupEntity = userEntity.getGroup();
        Hibernate.initialize(groupEntity.getTables());
        Set<TableEntity> tableEntities = groupEntity.getTables();
        List<ProjectEntity> projectEntities = new LinkedList<>();
        for (TableEntity tableEntity : tableEntities) {
            ProjectEntity projectEntity = tableEntity.getProject();
            projectEntities.add(projectEntity);
        }
        return projectEntities;
    }

    private UserRepository getUserRepository() {
        return (UserRepository) this.getAbstractRepository();
    }

}
