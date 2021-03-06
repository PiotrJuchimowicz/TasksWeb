package com.company.project.service;


import com.company.project.model.MessageEntity;

import java.util.List;

public interface MessageService extends AbstractService<MessageEntity> {
    List<MessageEntity> findMessageEntitiesByRecipientAndRead(Long recipientId, boolean read);
}
