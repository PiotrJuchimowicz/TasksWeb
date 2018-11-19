package com.company.project.controller;

import com.company.project.dto.ConversationDto;
import com.company.project.mapper.AbstractMapper;
import com.company.project.model.ConversationEntity;
import com.company.project.service.AbstractService;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/conversations")
public class ConversationController extends AbstractController<ConversationEntity, ConversationDto> {
    @Autowired
    public ConversationController(AbstractMapper<ConversationEntity, ConversationDto> abstractMapper, AbstractService<ConversationEntity> abstractService) {
        super(abstractMapper, abstractService, LoggerFactory.getLogger(ConversationController.class));
    }
}
