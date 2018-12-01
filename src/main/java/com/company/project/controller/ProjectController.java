package com.company.project.controller;

import com.company.project.dto.ProjectDto;
import com.company.project.mapper.AbstractMapper;
import com.company.project.model.ProjectEntity;
import com.company.project.service.AbstractService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/projects")
@CrossOrigin(origins = "http://localhost:4200")
public class ProjectController extends AbstractController<ProjectEntity, ProjectDto>
{
    @Autowired
    public ProjectController(AbstractMapper<ProjectEntity, ProjectDto> abstractMapper, AbstractService<ProjectEntity> abstractService) {
        super(abstractMapper, abstractService, LoggerFactory.getLogger(ProjectController.class));
    }
}
