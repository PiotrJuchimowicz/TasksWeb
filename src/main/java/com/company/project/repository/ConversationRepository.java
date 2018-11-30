package com.company.project.repository;

import com.company.project.model.ConversationEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.util.List;

@Repository
public interface ConversationRepository extends AbstractRepository<ConversationEntity> {
    @Query(value ="SELECT conversation_t.id " +
            "FROM conversation_t  INNER JOIN message_t message ON conversation_t.id = message.conversation_id " +
            "WHERE (message.sender_id=:userId OR message.recipient_id=:userId) " +
            "GROUP BY conversation_t.id,message.post_date " +
            "HAVING message.post_date = " +
                                    "(SELECT max(message2.post_date) " +
                                    " FROM message_t message2 " +
                                    " WHERE message2.conversation_id = conversation_t.id) " +
            "ORDER BY message.post_date DESC "
            ,nativeQuery = true)
    List<BigInteger> findSortedConversationsIdByLastMessageDESC(@Param("userId") BigInteger userId);
}
