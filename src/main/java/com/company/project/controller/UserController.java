package com.company.project.controller;

import com.company.project.dto.UserDto;
import com.company.project.mapper.UserMapper;
import com.company.project.model.UserEntity;
import com.company.project.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController extends AbstractController<UserEntity, UserDto> {
    @Autowired
    public UserController(UserService userService, UserMapper userMapper) {
        super(userService, userMapper);
    }
}
