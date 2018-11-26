package com.company.project.service_impl;

import com.company.project.model.ConversationEntity;
import com.company.project.model.MessageEntity;
import com.company.project.repository.AbstractRepository;
import com.company.project.repository.ConversationRepository;
import com.company.project.repository.MessageRepository;
import com.company.project.service.ConversationService;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

@Service
@Transactional
public class ConversationServiceImpl extends AbstractServiceImpl<ConversationEntity> implements ConversationService {
    private MessageRepository messageRepository;

    @Autowired
    public ConversationServiceImpl(AbstractRepository<ConversationEntity> abstractRepository,
                                   MessageRepository messageRepository) {
        super(abstractRepository);
        this.messageRepository = messageRepository;
    }

    @Override
    public ConversationEntity getConversationWithSortedMessages(Long conversationId) {
        ConversationEntity entity = read(conversationId);
        Hibernate.initialize(entity.getMessages());
        return entity;
    }

    @Override
    public List<ConversationEntity> findSortedConversationsByLastMessage(Long userId) {
        List<ConversationEntity> conversations = this.getConversationRepository().findSortedConversationsByLastMessage(userId);
        System.out.println(conversations);
        List<MessageEntity> latestMessages = new LinkedList<>();
        for (ConversationEntity conversationEntity : conversations) {
            Hibernate.initialize(conversationEntity.getMessages());
            List<MessageEntity> messages = new LinkedList<>(conversationEntity.getMessages());
            messages.sort(Comparator.comparing(MessageEntity::getPostDate).reversed());
            latestMessages.add(messages.get(0));
        }
        latestMessages.sort(Comparator.comparing(MessageEntity::getPostDate).reversed());
        System.out.println(latestMessages);
        List<ConversationEntity> sortedConversations = new LinkedList<>();
        for(MessageEntity messageEntity: latestMessages){
            ConversationEntity conversationEntity = messageEntity.getConversation();
            sortedConversations.add(conversationEntity);
        }
        return sortedConversations;
    }

    private ConversationRepository getConversationRepository() {
        return (ConversationRepository) this.getAbstractRepository();
    }
}
