package io.github.go4it.semanticsearch.service;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.ChatModelCallAdvisor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class ChatService {

    private final ChatClient librarianChatClient;
    private final ChatClient simpleChatClient;

    public ChatService(
            @Qualifier("librarianChatClient") ChatClient librarianChatClient,
            @Qualifier("simpleChatClient") ChatClient simpleChatClient
    ) {
        this.librarianChatClient = librarianChatClient;
        this.simpleChatClient = simpleChatClient;
    }

    public String sendMessage(String message) {
        return this.simpleChatClient.prompt()
                .user(message)
                .call()
                .content();
    }

    public String sendBusinessMessage(String message) {
        return this.librarianChatClient.prompt()
                .user(message)
                .call()
                .content();
    }
}
