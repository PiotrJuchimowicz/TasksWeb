package com.company.project.controller;

import com.company.project.dto.AccountDto;
import com.company.project.mapper.AbstractMapper;
import com.company.project.model.AccountEntity;
import com.company.project.service.AbstractService;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/accounts")
public class AccountController extends AbstractController<AccountEntity, AccountDto> {

    @Autowired
    public AccountController(AbstractMapper<AccountEntity, AccountDto> abstractMapper, AbstractService<AccountEntity> abstractService) {
        super(abstractMapper, abstractService, LoggerFactory.getLogger(AccountController.class));
    }
}
