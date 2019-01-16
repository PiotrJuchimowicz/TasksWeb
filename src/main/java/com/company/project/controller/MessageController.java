package com.company.project.controller;

import com.company.project.dto.MessageDto;
import com.company.project.mapper.AbstractMapper;
import com.company.project.model.MessageEntity;
import com.company.project.service.AbstractService;
import com.company.project.service.MessageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedList;
import java.util.List;

@RestController("KIKI")
@RequestMapping("/messages")
@CrossOrigin(origins = "http://localhost:4200")
public class MessageController extends AbstractController<MessageEntity, MessageDto> {
    @Autowired
    public MessageController(AbstractMapper<MessageEntity, MessageDto> abstractMapper, AbstractService<MessageEntity> abstractService) {
        super(abstractMapper, abstractService, LoggerFactory.getLogger(MessageController.class));
    }

    @GetMapping("/byRead")
    public List<MessageDto> getUserMessagesByRead(@RequestParam("recipientId") Long recipientId,
                                                  @RequestParam("read") Boolean isRead) {
        Logger log = this.getLogger();
        log.info("Getting messages with property read: " + isRead + " and property recipientId: " + recipientId);
        List<MessageEntity> messageEntities = getMessageService().findMessageEntitiesByRecipientAndRead(recipientId, isRead);
        List<MessageDto> messageDtos = new LinkedList<>();
        for (MessageEntity messageEntity : messageEntities) {
            MessageDto messageDto = this.getAbstractMapper().fromEntityToNewDto(messageEntity);
            messageDtos.add(messageDto);
        }
        return messageDtos;
    }



    @Override
    public void deleteOne(Long id) {
        throw new UnsupportedOperationException("Operation is not supprted");
    }

    @Override
    public void deleteAll() {
        throw new UnsupportedOperationException("Operation is not supported");
    }

    private MessageService getMessageService() {
        return (MessageService) this.getAbstractService();
    }
}
