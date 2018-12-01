package com.company.project.mapper;

import com.company.project.dto.TableDto;
import com.company.project.model.TableEntity;

public class TableMapper implements AbstractMapper<TableEntity, TableDto> {
    @Override
    public void fromDtoToExistingEntity(TableDto dto, TableEntity entity) {

    }

    @Override
    public TableEntity fromDtoToNewEntity(TableDto dto) {
        return null;
    }

    @Override
    public TableDto fromEntityToNewDto(TableEntity entity) {
        return null;
    }
}
