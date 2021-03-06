package com.company.project.controller;

import com.company.project.dto.GroupDto;
import com.company.project.dto.TableDto;
import com.company.project.dto.UserDto;
import com.company.project.mapper.AbstractMapper;
import com.company.project.model.GroupEntity;
import com.company.project.model.TableEntity;
import com.company.project.model.UserEntity;
import com.company.project.service.AbstractService;
import com.company.project.service.GroupService;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedHashSet;
import java.util.Set;

@RestController
@RequestMapping("/groups")
@CrossOrigin(origins = "http://localhost:4200")
public class GroupController extends AbstractController<GroupEntity, GroupDto> {
    private AbstractMapper<UserEntity, UserDto> userMapper;
    private AbstractMapper<TableEntity, TableDto> tableMapper;

    @Autowired
    public GroupController(AbstractMapper<GroupEntity, GroupDto> abstractMapper, AbstractMapper<TableEntity, TableDto> tableMapper,
                           AbstractService<GroupEntity> abstractService, AbstractMapper<UserEntity, UserDto> userMapper) {
        super(abstractMapper, abstractService, LoggerFactory.getLogger(GroupController.class));
        this.userMapper = userMapper;
        this.tableMapper = tableMapper;
    }

    @GetMapping("/withUsers/{groupId}")
    public GroupDto getGroupWithUsers(@PathVariable("groupId") Long groupId) {
        this.getLogger().info("Getting group with users with groupId: " + groupId);
        GroupEntity groupEntity = this.getGroupService().getGroupWithUsers(groupId);
        GroupDto groupDto = this.getAbstractMapper().fromEntityToNewDto(groupEntity);
        Set<UserEntity> userEntities = groupEntity.getUsersInGroup();
        Set<UserDto> userDtos = new LinkedHashSet<>();
        for (UserEntity userEntity : userEntities) {
            UserDto userDto = this.userMapper.fromEntityToNewDto(userEntity);
            userDto.setRoles(null);
            userDto.setAccount(null);
            userDtos.add(userDto);
        }
        groupDto.setUsers(userDtos);
        return groupDto;
    }

    @GetMapping("/withTables/{groupId}")
    public GroupDto getGroupWithTables(@PathVariable("groupId") Long groupId) {
        this.getLogger().info("Getting group with tables with groupId: " + groupId);
        GroupEntity groupEntity = this.getGroupService().getGroupWithTables(groupId);
        GroupDto groupDto = this.getAbstractMapper().fromEntityToNewDto(groupEntity);
        Set<TableEntity> tableEntities = groupEntity.getTables();
        Set<TableDto> tableDtos = new LinkedHashSet<>();
        for (TableEntity tableEntity : tableEntities) {
            TableDto tableDto = this.tableMapper.fromEntityToNewDto(tableEntity);
            tableDtos.add(tableDto);
        }
        groupDto.setTables(tableDtos);
        return groupDto;
    }

    @DeleteMapping("/removeUser")
    public void removeUserFromGroup(@RequestParam("userId") Long userId,
                                    @RequestParam("groupId") Long groupId) {
        this.getLogger().info("Removing user with id: " + userId + " from group with id: " + groupId);
        this.getGroupService().removeUserFromGroup(userId, groupId);
    }

    private GroupService getGroupService() {
        return (GroupService) this.getAbstractService();
    }


}
