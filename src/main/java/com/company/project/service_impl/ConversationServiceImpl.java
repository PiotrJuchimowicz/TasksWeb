package com.company.project.service_impl;

import com.company.project.model.ConversationEntity;
import com.company.project.repository.AbstractRepository;
import com.company.project.repository.ConversationRepository;
import com.company.project.service.ConversationService;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ConversationServiceImpl extends AbstractServiceImpl<ConversationEntity> implements ConversationService {
    @Autowired
    public ConversationServiceImpl(AbstractRepository<ConversationEntity> abstractRepository){
       super(abstractRepository);
   }

    @Override
    public ConversationEntity getConversationWithSortedMessages(Long conversationId) {
        ConversationEntity entity = read(conversationId);
        Hibernate.initialize(entity.getMessages());
        return entity;
    }

    private ConversationRepository getConversationRepository(){
        return (ConversationRepository) this.getAbstractRepository();
    }
}
