package com.company.project.service_impl;

import com.company.project.model.ConversationEntity;
import com.company.project.repository.AbstractRepository;
import com.company.project.service.ConversationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ConversationServiceImpl extends AbstractServiceImpl<ConversationEntity> implements ConversationService {
    @Autowired
    public ConversationServiceImpl(AbstractRepository<ConversationEntity> abstractRepository){
       super(abstractRepository);
   }
}
