package com.company.project.mapper;

import com.company.project.dto.AbstractDto;
import com.company.project.model.AbstractEntity;

public interface AbstractMapper<T extends AbstractEntity,K extends AbstractDto> {
    T fromDtoToEntity(K dto);
    K fromEntityToDto(T entity);
}
