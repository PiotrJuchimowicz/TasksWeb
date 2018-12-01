package com.company.project.mapper;

import com.company.project.dto.GroupDto;
import com.company.project.model.GroupEntity;

public class GroupMapper implements AbstractMapper<GroupEntity, GroupDto> {
    @Override
    public void fromDtoToExistingEntity(GroupDto dto, GroupEntity entity) {

    }

    @Override
    public GroupEntity fromDtoToNewEntity(GroupDto dto) {
        return null;
    }

    @Override
    public GroupDto fromEntityToNewDto(GroupEntity entity) {
        return null;
    }
}
