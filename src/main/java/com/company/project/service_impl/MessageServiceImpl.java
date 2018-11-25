package com.company.project.service_impl;


import com.company.project.model.MessageEntity;
import com.company.project.repository.AbstractRepository;
import com.company.project.repository.MessageRepository;
import com.company.project.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class MessageServiceImpl extends AbstractServiceImpl<MessageEntity> implements MessageService {
    @Autowired
    public MessageServiceImpl(AbstractRepository<MessageEntity> abstractRepository) {
        super(abstractRepository);
    }

    @Override
    public List<MessageEntity> findMessageEntitiesByRecipientAndRead(Long recipientId,boolean read) {
        return  getMessageRepository().findMessageEntitiesByRecipientIdAndIsRead(recipientId,read);
    }

    private MessageRepository getMessageRepository(){
        return (MessageRepository) this.getAbstractRepository();
    }
}
