package com.company.project.controller;

import com.company.project.model.AccountEntity;
import com.company.project.service.AccountService;
import com.company.project.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class SecurityController {
    private UserService userService;
    private AccountService accountService;

    @Autowired
    public SecurityController(UserService userService, AccountService accountService) {
        this.userService = userService;
        this.accountService = accountService;
    }

    @RequestMapping("/login")
    public Long login(@RequestBody Map<String, String> request) {
        String email = request.get("email");
        String password = request.get("password");
        String passwordHash = String.valueOf(password.hashCode());
        AccountEntity accountEntity = accountService.findByEmail(email);
        if (!accountEntity.getPassword().equals(passwordHash)) {
            throw new SecurityException("Unable to login with email: " + email);
        }
        return userService.findByAccount(accountEntity).getId();
    }
}
