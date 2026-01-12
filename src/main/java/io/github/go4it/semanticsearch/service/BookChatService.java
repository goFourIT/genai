package io.github.go4it.semanticsearch.service;

import io.github.go4it.semanticsearch.domain.entity.Conversation;
import io.github.go4it.semanticsearch.domain.entity.Message;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class BookChatService {

    private final ChatClient librarianChatClient;

    public BookChatService(@Qualifier("librarianChatClient") ChatClient librarianChatClient) {
        this.librarianChatClient = librarianChatClient;
    }


    public void createConversation(Conversation conversation) {

    }

    public Message createMessageInConversation(String conversationId, Message conversation) {
        return null;
    }

    public Message sendMessage(Message message) {
        String responseContent = this.librarianChatClient.prompt()
                .user(message.getContent())
                .call()
                .content();

        Message response = new Message();
        response.setId(UUID.randomUUID().toString());
        response.setContent(responseContent);
        return response;
    }
}
