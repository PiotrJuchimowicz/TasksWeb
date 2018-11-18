package com.company.project.service_impl;

import com.company.project.exception.ObjectAlreadyExistsException;
import com.company.project.exception.UnableToFindObjectException;
import com.company.project.exception.UnableToGetIdException;
import com.company.project.model.AbstractEntity;
import com.company.project.repository.AbstractRepository;
import com.company.project.service.AbstractService;
import lombok.Getter;
import lombok.Setter;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Optional;

@Getter
@Setter
public abstract class AbstractServiceImpl<T extends AbstractEntity> implements AbstractService<T> {
    private AbstractRepository<T> abstractRepository;

    protected AbstractServiceImpl(AbstractRepository<T> abstractRepository) {
        this.abstractRepository = abstractRepository;
    }

    @Override
    public T read(Long id) {
        Optional<T> result = abstractRepository.findById(id);
        if (result.isPresent())
            return result.get();
        throw new UnableToFindObjectException("Unable to find object with id: " + id);
    }

    @Override
    public T create(T object) {
        if (ifObjectExistsInDatabase(object))
            throw new ObjectAlreadyExistsException("Object " + object + " already exists");

        return abstractRepository.save(object);
    }

    @Override
    public T update(T object) {
        if (!ifObjectExistsInDatabase(object))
            throw new UnableToFindObjectException("Unable to find object " + object + " in database");
        return abstractRepository.save(object);
    }

    @Override
    public void deleteById(Long id) {
        read(id);
        abstractRepository.deleteById(id);
    }

    @Override
    public void deleteAll() {
        abstractRepository.deleteAll();
    }

    @Override
    public List<T> readAll() {
        return abstractRepository.findAll();

    }

    private boolean ifObjectExistsInDatabase(T object) {
        Field field = null;
        Field[] fields = object.getClass().getSuperclass().getDeclaredFields();
        String fieldName = fields[0].getName();
        Long fieldIdValue = null;
        try {
            field = object.getClass().getSuperclass().getDeclaredField(fieldName);
            field.setAccessible(true);
        } catch (NoSuchFieldException e) {
            throw new UnableToGetIdException("Unable to get object's id");
        }
        try {
            fieldIdValue = (Long) field.get(object);
        } catch (IllegalAccessException e) {
            throw new UnableToGetIdException("Unable to get object's id");
        }
        if (fieldIdValue == null)
            return false;
        return abstractRepository.existsById(fieldIdValue);
    }
}
