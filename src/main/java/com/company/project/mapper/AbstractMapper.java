package com.company.project.mapper;

import com.company.project.dto.AbstractDto;
import com.company.project.model.AbstractEntity;


public interface AbstractMapper<T extends AbstractEntity, K extends AbstractDto> {

    public abstract void fromDtoToExistingEntity(K dto, T entity);

    public abstract T fromDtoToNewEntity(K dto);

    public abstract K fromEntityToNewDto(T entity);
}
