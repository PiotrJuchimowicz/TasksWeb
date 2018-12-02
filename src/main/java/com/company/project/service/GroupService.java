package com.company.project.service;


import com.company.project.model.GroupEntity;

public interface GroupService extends AbstractService<GroupEntity> {
    GroupEntity getGroupWithUsers(Long groupId);
    void removeUserFromGroup(Long userId,Long groupId);
}
