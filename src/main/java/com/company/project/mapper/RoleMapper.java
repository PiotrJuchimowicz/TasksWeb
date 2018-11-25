package com.company.project.mapper;

import com.company.project.dto.RoleDto;
import com.company.project.exception.MapperException;
import com.company.project.model.RoleEntity;
import org.springframework.stereotype.Component;

@Component
public class RoleMapper implements AbstractMapper<RoleEntity, RoleDto> {
    @Override
    public void fromDtoToExistingEntity(RoleDto dto, RoleEntity entity) {
        if (dto == null || entity == null) {
            throw new MapperException("Unable to map from RoleDto to existing RoleEntity");
        }
        RoleEntity.Role roleValue = RoleEntity.Role.valueOf(dto.getRoleName());
        if (dto.getId() != null)
            entity.setId(dto.getId());
        entity.setRoleValue(roleValue);
    }

    @Override
    public RoleEntity fromDtoToNewEntity(RoleDto dto) {
        if (dto == null) {
            throw new MapperException("Unable to map from RoleDto to new RoleEntity");
        }
        RoleEntity entity = new RoleEntity();
        RoleEntity.Role roleValue = RoleEntity.Role.valueOf(dto.getRoleName());
        if (dto.getId() != null)
            entity.setId(dto.getId());
        entity.setRoleValue(roleValue);
        return entity;
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
