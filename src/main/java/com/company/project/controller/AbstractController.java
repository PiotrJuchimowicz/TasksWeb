package com.company.project.controller;

import com.company.project.dto.AbstractDto;
import com.company.project.mapper.AbstractMapper;
import com.company.project.model.AbstractEntity;
import com.company.project.service.AbstractService;
import org.slf4j.Logger;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedList;
import java.util.List;


public abstract class AbstractController<T extends AbstractEntity, K extends AbstractDto> {
    private AbstractMapper<T, K> abstractMapper;
    private AbstractService<T> abstractService;
    private Logger log;


    protected AbstractController(AbstractMapper<T, K> abstractMapper, AbstractService<T> abstractService,
                                 Logger log) {
        this.abstractMapper = abstractMapper;
        this.abstractService = abstractService;
        this.log = log;
    }

    @PostMapping
    public K add(@RequestBody K dto) {
        log.info("Adding new object");
        log.info("Received json: " + dto);
        T entity = abstractMapper.fromDtoToNewEntity(dto);
        log.info("Adding: " + entity);
        entity = abstractService.create(entity);
        return abstractMapper.fromEntityToNewDto(entity);
    }

    @PatchMapping("/{id}")
    public K update(@PathVariable("id") Long id, @RequestBody K dto) {
        log.info("Updating object with id:" + id);
        log.info("Received json: " + dto);
        T entity = abstractService.read(id);
        abstractMapper.fromDtoToExistingEntity(dto, entity);
        entity = abstractService.update(entity);
        log.info("Updated entity: " + entity);
        return abstractMapper.fromEntityToNewDto(entity);

    }

    @GetMapping("/{id}")
    public K getOne(@PathVariable("id") Long id) {
        log.info("Getting object with id: " + id);
        T entity = abstractService.read(id);
        log.info("Found: " + entity);
        return abstractMapper.fromEntityToNewDto(entity);
    }

    @GetMapping()
    public List<K> getAll() {
        log.info("Getting all objects");
        List<T> resultFromDatabase = abstractService.readAll();
        List<K> resultFromDatabaseMapped = new LinkedList<>();
        for (T entity : resultFromDatabase) {
            K dto = abstractMapper.fromEntityToNewDto(entity);
            resultFromDatabaseMapped.add(dto);
        }
        return resultFromDatabaseMapped;
    }

    @DeleteMapping("/{id}")
    public void deleteOne(@PathVariable("id") Long id) {
        log.info("Deleting object with id= " + id);
        abstractService.deleteById(id);
    }

    @DeleteMapping
    public void deleteAll() {
        log.info("Deleting all objects");
        abstractService.deleteAll();
    }

    AbstractService<T> getAbstractService() {
        return abstractService;
    }

    AbstractMapper<T, K> getAbstractMapper() {
        return abstractMapper;
    }

    Logger getLogger() {
        return log;
    }
}
