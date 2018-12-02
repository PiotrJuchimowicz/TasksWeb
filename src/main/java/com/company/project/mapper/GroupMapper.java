package com.company.project.mapper;

import com.company.project.dto.GroupDto;
import com.company.project.exception.MapperException;
import com.company.project.model.GroupEntity;
import com.company.project.model.UserEntity;
import com.company.project.service.GroupService;
import com.company.project.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
public class GroupMapper implements AbstractMapper<GroupEntity, GroupDto> {
    private UserService userService;
    private GroupService groupService;

    @Autowired
    public GroupMapper(UserService userService,GroupService groupService){
        this.userService = userService;
        this.groupService = groupService;
    }

    @Override
    public void fromDtoToExistingEntity(GroupDto groupDto, GroupEntity groupEntity) {
        if(groupDto== null || groupEntity == null){
            throw new MapperException("Unable to map from GroupDto to existing GroupEntity");
        }
        String name = groupDto.getName();
        if(name!=null){
            groupEntity.setName(name);
        }
        Long userId = groupDto.getUserId();
        if(userId!=null){
            UserEntity userEntity = userService.read(userId);
            Set<UserEntity> userEntities = groupService.getGroupWithUsers(groupEntity.getId()).getUsersInGroup();
            groupEntity.setUsersInGroup(userEntities);
            groupEntity.addUserToGroup(userEntity);
        }
    }

    @Override
    public GroupEntity fromDtoToNewEntity(GroupDto groupDto) {
        if(groupDto==null){
            throw new MapperException("Unable to map from GroupDto to new GroupEntity");
        }
        GroupEntity groupEntity = new GroupEntity();
        groupEntity.setName(groupDto.getName());
        return groupEntity;
    }

    @Override
    public GroupDto fromEntityToNewDto(GroupEntity groupEntity) {
        if(groupEntity==null){
            throw new MapperException("Unable to map from GroupEntity to GroupDto");
        }
        GroupDto groupDto = new GroupDto();
        groupDto.setId(groupEntity.getId());
        groupDto.setName(groupEntity.getName());
        return groupDto;
    }
}
