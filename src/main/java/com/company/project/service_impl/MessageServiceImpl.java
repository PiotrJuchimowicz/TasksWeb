package com.company.project.service_impl;


import com.company.project.model.MessageEntity;
import com.company.project.repository.AbstractRepository;
import com.company.project.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class MessageServiceImpl extends AbstractServiceImpl<MessageEntity> implements MessageService {
    @Autowired
    public MessageServiceImpl(AbstractRepository<MessageEntity> abstractRepository) {
        super(abstractRepository);
    }
}
