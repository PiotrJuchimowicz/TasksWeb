package com.company.project.controller;

import com.company.project.dto.AbstractDto;
import com.company.project.mapper.AbstractMapper;
import com.company.project.model.AbstractEntity;
import com.company.project.model.UserEntity;
import com.company.project.service.AbstractService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedList;
import java.util.List;

public abstract class AbstractController<T extends AbstractEntity, K extends AbstractDto> {
    private AbstractService<T> abstractService;
    private AbstractMapper abstractMapper;
    private Logger log = LoggerFactory.getLogger(AbstractController.class);

    protected AbstractController(AbstractService<T> abstractService, AbstractMapper abstractMapper) {
        this.abstractService = abstractService;
        this.abstractMapper = abstractMapper;
    }

    @PostMapping
    @SuppressWarnings("unchecked")
    public K create(@RequestBody K dto) {
        log.info("Creating object");
        log.info("Received json: " + dto);
        T entity = (T) abstractMapper.fromDtoToEntity(dto);
        log.info("Adding: " + entity);
        entity = abstractService.create(entity);
        return (K) abstractMapper.fromEntityToDto(entity);
    }

    @PutMapping
    @SuppressWarnings("unchecked")
    K update(@RequestBody K dto) {
        log.info("Updating object");
        log.info("Received json: " + dto);
        T entity = (T) abstractMapper.fromDtoToEntity(dto);
        log.info("Mapped dto to entity: " + entity);
        entity = abstractService.update(entity);
        System.out.println(entity);
        return (K) abstractMapper.fromEntityToDto(entity);
    }

    @GetMapping("/{id}")
    @SuppressWarnings("unchecked")
    public K getOne(@PathVariable("id") Long id) {
        log.info("Getting object with id: " + id);
        T entity = (T) abstractService.read(id);
        log.info("Found: " + entity);
        return (K) abstractMapper.fromEntityToDto(entity);
    }

    @GetMapping
    @SuppressWarnings("unchecked")
    public List<K> getAll() {
        log.info("Getting all objects");
        List<T> resultFromDatabase = abstractService.readAll();
        List<K> resultFromDatabaseMapped = new LinkedList<>();
        for (T entity : resultFromDatabase) {
            K dto = (K) abstractMapper.fromEntityToDto(entity);
            resultFromDatabaseMapped.add(dto);
        }
        return resultFromDatabaseMapped;
    }

    @DeleteMapping("/{id}")
    public void deleteOne(@PathVariable("id") Long id) {
        log.info("Deleting object with id= " + id);
        abstractService.deleteById(id);
    }
}
