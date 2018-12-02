package com.company.project.controller;

import com.company.project.dto.TableDto;
import com.company.project.mapper.AbstractMapper;
import com.company.project.model.TableEntity;
import com.company.project.service.AbstractService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/tables")
@CrossOrigin(origins = "http://localhost:4200")
public class TableController extends AbstractController<TableEntity, TableDto> {
    @Autowired
    public TableController(AbstractMapper<TableEntity, TableDto> abstractMapper, AbstractService<TableEntity> abstractService) {
        super(abstractMapper, abstractService, LoggerFactory.getLogger(TableController.class));
    }
}
