package com.company.project.controller;

import com.company.project.dto.RoleDto;
import com.company.project.mapper.AbstractMapper;
import com.company.project.model.RoleEntity;
import com.company.project.service.AbstractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/roles")
public class RoleController extends AbstractController<RoleEntity, RoleDto> {
    @Autowired
    public RoleController(AbstractMapper<RoleEntity, RoleDto> abstractMapper, AbstractService<RoleEntity> abstractService) {
        super(abstractMapper, abstractService);
    }
}
