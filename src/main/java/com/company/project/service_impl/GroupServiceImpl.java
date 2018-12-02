package com.company.project.service_impl;


import com.company.project.exception.UnableToFindObjectException;
import com.company.project.model.GroupEntity;
import com.company.project.model.UserEntity;
import com.company.project.repository.AbstractRepository;
import com.company.project.service.GroupService;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.Set;

@Service
@Transactional
public class GroupServiceImpl extends AbstractServiceImpl<GroupEntity> implements GroupService {
    @Autowired
    public GroupServiceImpl(AbstractRepository<GroupEntity> abstractRepository) {
        super(abstractRepository);
    }

    @Override
    public GroupEntity getGroupWithUsers(Long groupId) {
        GroupEntity groupEntity = this.read(groupId);
        Hibernate.initialize(groupEntity.getUsersInGroup());
        return groupEntity;
    }

    @Override
    public void removeUserFromGroup(Long userId, Long groupId) {
        GroupEntity groupEntity = this.read(groupId);
        Hibernate.initialize(groupEntity.getUsersInGroup());
        Set<UserEntity> userEntities = groupEntity.getUsersInGroup();
        Optional<UserEntity> userEntityOptional = userEntities.stream()
                        .filter(userEntity -> userEntity.getId()
                        .equals(userId)).findAny();
        if(userEntityOptional.isPresent()){
            UserEntity userEntity = userEntityOptional.get();
            groupEntity.removeUserFromGroup(userEntity);
        }
        else
            throw new UnableToFindObjectException("Unable to find user with id: " + userId);
    }
}
