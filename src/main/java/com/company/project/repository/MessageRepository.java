package com.company.project.repository;

import com.company.project.model.MessageEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MessageRepository extends AbstractRepository<MessageEntity> {
    List<MessageEntity> findMessageEntitiesByRecipientIdAndIsRead(Long recipientId, boolean read);

    @Query("SELECT messageEntity FROM MessageEntity messageEntity " +
            "WHERE messageEntity.recipient.id=:userId OR messageEntity.sender.id=:userId" +
            " ORDER BY messageEntity.postDate ASC ")
    List<MessageEntity> findLastUserMessagesInConversations(@Param("userId") Long userId);
}
