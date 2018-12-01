package com.company.project.service_impl;

import com.company.project.model.ConversationEntity;
import com.company.project.repository.AbstractRepository;
import com.company.project.repository.ConversationRepository;
import com.company.project.repository.MessageRepository;
import com.company.project.service.ConversationService;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigInteger;
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
        List<BigInteger> selectedIds = this.getConversationRepository().findSortedConversationsIdByLastMessageDESC(BigInteger.valueOf(userId));
        List<ConversationEntity> sortedConversations = new LinkedList<>();
        for (BigInteger id : selectedIds) {
            ConversationEntity entity = read(id.longValue());
            sortedConversations.add(entity);
        }
        return sortedConversations;
    }

    private ConversationRepository getConversationRepository() {
        return (ConversationRepository) this.getAbstractRepository();
    }
}
