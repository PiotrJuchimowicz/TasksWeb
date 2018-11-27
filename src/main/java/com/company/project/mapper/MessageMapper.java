package com.company.project.mapper;

import com.company.project.dto.MessageDto;
import com.company.project.exception.MapperException;
import com.company.project.exception.UnableToFindUserWithEmail;
import com.company.project.model.ConversationEntity;
import com.company.project.model.MessageEntity;
import com.company.project.model.UserEntity;
import com.company.project.service.ConversationService;
import com.company.project.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MessageMapper implements AbstractMapper<MessageEntity, MessageDto> {
    private UserService userService;
    private ConversationService conversationService;

    @Autowired
    public MessageMapper(UserService userService, ConversationService conversationService) {
        this.userService = userService;
        this.conversationService = conversationService;
    }

    @Override
    public void fromDtoToExistingEntity(MessageDto messageDto, MessageEntity messageEntity) {
        if (messageDto == null || messageEntity == null) {
            throw new MapperException("Unable to map from MessageDto to existing MessageEntity");
        }
        UserEntity recipientEntity, senderEntity;
        recipientEntity = userService.findByEmail(messageDto.getRecipientEmail());
        if (recipientEntity == null) {
            throw new UnableToFindUserWithEmail("Unable to find user with email: " + messageDto.getRecipientEmail());
        }
        senderEntity = userService.findByEmail(messageDto.getSenderEmail());
        if(senderEntity==null){
            throw new UnableToFindUserWithEmail("Unable to find user with email: " + messageDto.getRecipientEmail());
        }
        if (messageDto.getId() != null)
            messageEntity.setId(messageDto.getId());
        messageEntity.setSender(senderEntity);
        messageEntity.setRecipient(recipientEntity);
        ConversationEntity conversationEntity = conversationService.read(messageDto.getConversationId());
        messageEntity.setConversation(conversationEntity);
        messageEntity.setBody(messageDto.getBody());
        messageEntity.setRead(messageDto.isRead());
        messageEntity.setPostDate(messageDto.getPostDate());
    }

    @Override
    public MessageEntity fromDtoToNewEntity(MessageDto messageDto) {
        if (messageDto == null) {
            throw new MapperException("Unable to map from MessageDto to new MessageEntity");
        }
        UserEntity recipientEntity, senderEntity;
        recipientEntity = userService.findByEmail(messageDto.getRecipientEmail());
        if (recipientEntity == null) {
            throw new UnableToFindUserWithEmail("Unable to find user with email: " + messageDto.getRecipientEmail());
        }
        senderEntity = userService.findByEmail(messageDto.getSenderEmail());
        if(senderEntity==null){
            throw new UnableToFindUserWithEmail("Unable to find user with email: " + messageDto.getRecipientEmail());
        }
        MessageEntity messageEntity = new MessageEntity();
        if (messageDto.getId() != null)
            messageEntity.setId(messageDto.getId());
        messageEntity.setSender(senderEntity);
        messageEntity.setRecipient(recipientEntity);
        ConversationEntity conversationEntity = conversationService.read(messageDto.getConversationId());
        messageEntity.setConversation(conversationEntity);
        messageEntity.setBody(messageDto.getBody());
        messageEntity.setRead(messageDto.isRead());
        messageEntity.setPostDate(messageDto.getPostDate());
        return messageEntity;
    }

    @Override
    public MessageDto fromEntityToNewDto(MessageEntity messageEntity) {
        if (messageEntity == null) {
            throw new MapperException("Unable to map from MessageEntity to MessageDto");
        }
        MessageDto messageDto = new MessageDto();
        messageDto.setId(messageEntity.getId());
        messageDto.setBody(messageEntity.getBody());
        messageDto.setRead(messageEntity.isRead());
        messageDto.setPostDate(messageEntity.getPostDate());
        messageDto.setConversationId(messageEntity.getConversation().getId());
        messageDto.setRecipientEmail(messageEntity.getRecipient().getAccount().getEmail());
        messageDto.setSenderEmail(messageEntity.getSender().getAccount().getEmail());
        return messageDto;
    }
}
