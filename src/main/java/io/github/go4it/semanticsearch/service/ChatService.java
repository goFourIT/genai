package io.github.go4it.semanticsearch.service;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class ChatService {

    private final ChatClient simpleChatClient;

    public ChatService(
            @Qualifier("simpleChatClient") ChatClient simpleChatClient
    ) {
        this.simpleChatClient = simpleChatClient;
    }

    public String sendMessage(String message) {
        return this.simpleChatClient.prompt()
                .user(message)
                .call()
                .content();
    }
}
