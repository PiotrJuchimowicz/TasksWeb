package com.company.project.controller;

import com.company.project.dto.MessageDto;
import com.company.project.mapper.AbstractMapper;
import com.company.project.model.MessageEntity;
import com.company.project.service.AbstractService;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/messages")
public class MessageController extends AbstractController<MessageEntity, MessageDto> {
    protected MessageController(AbstractMapper<MessageEntity, MessageDto> abstractMapper, AbstractService<MessageEntity> abstractService) {
        super(abstractMapper, abstractService, LoggerFactory.getLogger(MessageController.class));
    }
}
