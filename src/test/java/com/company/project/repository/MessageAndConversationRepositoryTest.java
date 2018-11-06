package com.company.project.repository;

import com.company.project.model.ConversationEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;


@ExtendWith(SpringExtension.class)
@SpringBootTest
class MessageAndConversationRepositoryTest {
    UserRepository userRepository;
    ConversationRepository conversationRepository;
    MessageRepository messageRepository;

    @Autowired
    MessageAndConversationRepositoryTest(UserRepository userRepository,
                                         ConversationRepository conversationRepository,
                                         MessageRepository messageRepository) {
        this.userRepository = userRepository;
        this.conversationRepository = conversationRepository;
        this.messageRepository = messageRepository;
    }

    @Test
    void createConversationWithMessages() {
        ConversationEntity conversationEntity = new ConversationEntity();
    }

}
