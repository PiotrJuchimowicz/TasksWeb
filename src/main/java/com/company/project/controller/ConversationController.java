package com.company.project.controller;

import com.company.project.dto.ConversationDto;
import com.company.project.dto.MessageDto;
import com.company.project.mapper.AbstractMapper;
import com.company.project.mapper.MessageMapper;
import com.company.project.model.ConversationEntity;
import com.company.project.model.MessageEntity;
import com.company.project.service.AbstractService;
import com.company.project.service.ConversationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedList;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/conversations")
@CrossOrigin(origins = "http://localhost:4200")
public class ConversationController extends AbstractController<ConversationEntity, ConversationDto> {
    private AbstractMapper<MessageEntity,MessageDto> messageMapper;
    @Autowired
    public ConversationController(AbstractMapper<ConversationEntity, ConversationDto> abstractMapper, AbstractService<ConversationEntity> abstractService,
                                  AbstractMapper<MessageEntity,MessageDto> messageMapper) {
        super(abstractMapper, abstractService, LoggerFactory.getLogger(ConversationController.class));
        this.messageMapper = messageMapper;
    }

    @GetMapping("/conversationWithSortedMessages/{conversationId}")
    public ConversationDto getConversationWithSortedMessages(@PathVariable("conversationId") Long conversationId){
        Logger log = this.getLogger();
        log.info("Getting conversation with id: " + conversationId + " and with sorted messages");
        ConversationEntity conversationEntity = getConversationService().getConversationWithSortedMessages(conversationId);
        List<MessageDto> messageDtos = new LinkedList<>();
        Set<MessageEntity> messageEntities = conversationEntity.getMessages();
        for(MessageEntity messageEntity : messageEntities){
            MessageDto messageDto = messageMapper.fromEntityToNewDto(messageEntity);
            messageDtos.add(messageDto);
        }

        ConversationDto conversationDto = this.getAbstractMapper().fromEntityToNewDto(conversationEntity);
        conversationDto.setMessages(messageDtos);
        return conversationDto;
    }


    private ConversationService getConversationService(){
        return (ConversationService) this.getAbstractService();
    }
}
