package com.company.project.mapper;

import com.company.project.dto.AbstractDto;
import com.company.project.model.AbstractEntity;

public interface AbstractMapper<T extends AbstractEntity,K extends AbstractDto> {
    void fromDtoToEntity(K dto,T entity);
    void fromEntityToDto(T entity,K dto);
}
