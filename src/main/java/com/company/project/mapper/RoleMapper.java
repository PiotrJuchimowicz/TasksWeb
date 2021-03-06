package com.company.project.mapper;

import com.company.project.dto.RoleDto;
import com.company.project.exception.MapperException;
import com.company.project.model.RoleEntity;
import com.company.project.model.UserEntity;
import com.company.project.service.UserService;
import org.springframework.beans.InvalidPropertyException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.LinkedList;
import java.util.List;
import java.util.Set;

@Component
public class RoleMapper implements AbstractMapper<RoleEntity, RoleDto> {
    private UserService userService;

    @Autowired
    public RoleMapper(UserService userService) {
        this.userService = userService;
    }

    @Override
    public void fromDtoToExistingEntity(RoleDto dto, RoleEntity entity) {
        throw new UnsupportedOperationException("This operation is not supported");
    }

    @Override
    public RoleEntity fromDtoToNewEntity(RoleDto dto) {
        if (dto == null) {
            throw new MapperException("Unable to map from RoleDto to new RoleEntity");
        }
        RoleEntity roleEntity = new RoleEntity();
        RoleEntity.Role roleValue = RoleEntity.Role.valueOf(dto.getRoleName());
        roleEntity.setRoleValue(roleValue);
        return roleEntity;
    }

    @Override
    public RoleDto fromEntityToNewDto(RoleEntity entity) {
        if (entity == null) {
            throw new MapperException("Unable to map from RoleEntity to RoleDto");
        }
        RoleDto dto = new RoleDto();
        dto.setId(entity.getId());
        dto.setRoleName(entity.getRoleValue().toString());
        dto.setUserId(entity.getUser().getId());
        return dto;
    }



}
