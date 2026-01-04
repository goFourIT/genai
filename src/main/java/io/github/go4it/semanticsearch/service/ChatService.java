package io.github.go4it.semanticsearch.service;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.stereotype.Service;

@Service
public class ChatService {

    private final ChatClient chatClient;

    public ChatService(ChatClient chatClient) {
        this.chatClient = chatClient;
    }

    public String sendMessage(String message) {
        return this.chatClient.prompt()
                .user(message)
                .call()
                .content();
    }
}
