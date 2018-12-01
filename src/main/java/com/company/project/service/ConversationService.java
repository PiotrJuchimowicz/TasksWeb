package com.company.project.service;


import com.company.project.model.ConversationEntity;

import java.util.List;

public interface ConversationService extends AbstractService<ConversationEntity> {
    ConversationEntity getConversationWithSortedMessages(Long conversationId);

    List<ConversationEntity> findSortedConversationsByLastMessage(Long userId);
}
