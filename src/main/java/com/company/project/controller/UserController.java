package com.company.project.controller;

import com.company.project.dto.UserDto;
import com.company.project.mapper.AbstractMapper;
import com.company.project.model.UserEntity;
import com.company.project.service.AbstractService;
import com.company.project.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedList;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController extends AbstractController<UserEntity,UserDto> {
    @Autowired
    public UserController(AbstractMapper<UserEntity, UserDto> abstractMapper, AbstractService<UserEntity> abstractService) {
        super(abstractMapper, abstractService,LoggerFactory.getLogger(UserController.class));
    }

    private UserService getUserService(){
        return (UserService) this.getAbstractService();
    }
}
