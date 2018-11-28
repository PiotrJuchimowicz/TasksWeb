package com.company.project.controller;

import com.company.project.dto.RoleDto;
import com.company.project.mapper.AbstractMapper;
import com.company.project.model.RoleEntity;
import com.company.project.service.AbstractService;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/roles")
@CrossOrigin(origins = "http://localhost:4200")
public class RoleController extends AbstractController<RoleEntity, RoleDto> {
    @Autowired
    public RoleController(AbstractMapper<RoleEntity, RoleDto> abstractMapper, AbstractService<RoleEntity> abstractService) {
        super(abstractMapper, abstractService, LoggerFactory.getLogger(RoleController.class));
    }

    @Override
    public RoleDto update(Long id, RoleDto dto) {
        throw new UnsupportedOperationException("This operation is not supported");
    }

    @Override
    public void deleteOne(@PathVariable("id") Long id) {
        RoleEntity roleEntity = getAbstractService().read(id);
        roleEntity.getUser().removeRole(roleEntity);
        super.deleteOne(id);
    }
}
