package com.company.project.repository;

import com.company.project.model.MessageEntity;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MessageRepository extends AbstractRepository<MessageEntity> {
    List<MessageEntity> findMessageEntitiesByRecipientIdAndIsRead(Long recipientId, boolean read);
}
