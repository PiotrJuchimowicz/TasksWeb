package com.company.project.mapper;

import com.company.project.dto.RoleDto;
import com.company.project.exception.MapperException;
import com.company.project.model.RoleEntity;
import org.springframework.stereotype.Component;

@Component
public class RoleMapper implements AbstractMapper<RoleEntity, RoleDto> {
    @Override
    public void fromDtoToEntity(RoleDto roleDto,RoleEntity roleEntity) {
        if(roleDto==null || roleEntity==null){
            throw new MapperException("Unable to map from RoleDto to RoleEntity");
        }
        roleEntity.setRoleValue(RoleEntity.Role.valueOf(roleDto.getRoleName()));
    }

    @Override
    public void fromEntityToDto(RoleEntity roleEntity,RoleDto roleDto) {
        if(roleEntity == null || roleDto==null) {
            throw new MapperException("Unable to map from RoleEntity to RoleDto");
        }
        roleDto.setId(roleEntity.getId());
        roleDto.setRoleName(roleEntity.getRoleValue().toString());
    }
}
