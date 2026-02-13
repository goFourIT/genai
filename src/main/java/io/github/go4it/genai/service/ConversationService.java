package io.github.go4it.genai.service;

import io.github.go4it.genai.domain.entity.Conversation;
import io.github.go4it.genai.repository.ConversationRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class ConversationService {

    private final ConversationRepository conversationRepository;

    public ConversationService(ConversationRepository conversationRepository) {
        this.conversationRepository = conversationRepository;
    }

    public List<Conversation> findAllConversations() {
        return this.conversationRepository.findAll();
    }

    public Conversation findConversationById(String conversationId) {
        return this.conversationRepository.findById(conversationId).orElseThrow();
    }

    public Conversation createConversation(Conversation conversation) {
        conversation.setMessages(new ArrayList<>());
        return this.conversationRepository.save(conversation);
    }
}
