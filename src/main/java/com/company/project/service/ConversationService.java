package com.company.project.service;


import com.company.project.model.ConversationEntity;

public interface ConversationService extends AbstractService<ConversationEntity> {
    ConversationEntity getConversationWithSortedMessages(Long conversationId);
}
