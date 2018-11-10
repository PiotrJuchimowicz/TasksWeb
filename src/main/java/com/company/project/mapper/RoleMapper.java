package com.company.project.mapper;

import com.company.project.dto.RoleDto;
import com.company.project.exception.MapperException;
import com.company.project.model.RoleEntity;
import org.springframework.stereotype.Component;

@Component
public class RoleMapper implements AbstractMapper<RoleEntity, RoleDto> {
    @Override
    public RoleEntity fromDtoToEntity(RoleDto roleDto) {
        if(roleDto==null){
            throw new MapperException("Unable to map from RoleDto to RoleEntity");
        }
        RoleEntity roleEntity = new RoleEntity();
        roleEntity.setRoleName(RoleEntity.Role.valueOf(roleDto.getRoleName()));
        return roleEntity;
    }

    @Override
    public RoleDto fromEntityToDto(RoleEntity roleEntity) {
        if(roleEntity == null){
            throw  new MapperException("Unable to map from RoleEntity to RoleDto");
        }
        RoleDto roleDto = new RoleDto();
        roleDto.setId(roleEntity.getId());
        roleDto.setRoleName(roleEntity.getRoleName().toString());
        return roleDto;
    }
}
