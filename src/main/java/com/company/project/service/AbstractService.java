package com.company.project.service;

import com.company.project.dto.AbstractDto;
import com.company.project.model.AbstractEntity;


import java.util.List;

public interface AbstractService<T extends AbstractEntity> {
    T create(T object);

    T update(T object);

    T read(Long id);

    void deleteById(Long id);

    void deleteAll();

    List<T> readAll();
}
