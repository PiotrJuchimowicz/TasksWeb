package com.company.project.repository;

import com.company.project.model.ConversationEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ConversationRepository extends AbstractRepository<ConversationEntity> {

    @Query("SELECT DISTINCT messageEntity.conversation " +
            "FROM MessageEntity messageEntity " +
            "WHERE (messageEntity.sender.id=:userId OR  messageEntity.recipient.id=:userId) ")
    List<ConversationEntity> findSortedConversationsByLastMessage(@Param("userId") Long userId);
}
