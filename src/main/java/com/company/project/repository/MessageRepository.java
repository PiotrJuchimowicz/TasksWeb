package com.company.project.repository;

import com.company.project.model.MessageEntity;
import org.springframework.stereotype.Repository;

@Repository
public interface MessageRepository extends AbstractRepository<MessageEntity> {
}
