package com.company.project.controller;

import com.company.project.dto.UserDto;
import com.company.project.mapper.AbstractMapper;
import com.company.project.model.UserEntity;
import com.company.project.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedList;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    private UserService userService;
    private AbstractMapper<UserEntity, UserDto> abstractMapper;
    private Logger log = LoggerFactory.getLogger(UserController.class);

    public UserController(UserService userService, AbstractMapper<UserEntity, UserDto> abstractMapper) {
        this.userService = userService;
        this.abstractMapper = abstractMapper;
    }

    @PostMapping
    public UserDto create(@RequestBody UserDto dto) {
        log.info("Creating user");
        log.info("Received json: " + dto);
        UserEntity entity = new UserEntity();
        abstractMapper.fromDtoToEntity(dto, entity);
        log.info("Adding: " + entity);
        entity = userService.create(entity);
        abstractMapper.fromEntityToDto(entity, dto);
        return dto;
    }

    @PutMapping("/{id}")
    UserDto update(@PathVariable("id") Long id, @RequestBody UserDto dto) {
        log.info("Updating user with id:" + id);
        log.info("Received json: " + dto);
        UserEntity entity = userService.read(id);
        abstractMapper.fromDtoToEntity(dto, entity);
        log.info("Mapped dto to entity: " + entity);
        entity = userService.update(entity);
        abstractMapper.fromEntityToDto(entity, dto);
        return dto;
    }

    @GetMapping("/{id}")
    public UserDto getOne(@PathVariable("id") Long id) {
        log.info("Getting user with id: " + id);
        UserEntity entity = userService.read(id);
        log.info("Found: " + entity);
        UserDto dto = new UserDto();
        abstractMapper.fromEntityToDto(entity, dto);
        return dto;
    }

    @GetMapping
    public List<UserDto> getAll() {
        log.info("Getting all users");
        List<UserEntity> resultFromDatabase = userService.readAll();
        List<UserDto> resultFromDatabaseMapped = new LinkedList<>();
        for (UserEntity entity : resultFromDatabase) {
            UserDto dto = new UserDto();
            abstractMapper.fromEntityToDto(entity, dto);
            resultFromDatabaseMapped.add(dto);
        }
        return resultFromDatabaseMapped;
    }

    @DeleteMapping("/{id}")
    public void deleteOne(@PathVariable("id") Long id) {
        log.info("Deleting user with id= " + id);
        userService.deleteById(id);
    }

    @DeleteMapping()
    public void deleteAll() {
        log.info("Deleting all users");
        userService.deleteAll();
    }
}
