package io.github.go4it.semanticsearch.service;

import io.github.go4it.semanticsearch.domain.dto.SendMessageDto;
import io.github.go4it.semanticsearch.domain.entity.Conversation;
import io.github.go4it.semanticsearch.domain.entity.Message;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class BookChatService {

    private final ConversationService conversationService;
    private final MessageService messageService;

    private final ChatClient librarianChatClient;

    public BookChatService(
            ConversationService conversationService,
            MessageService messageService,
            @Qualifier("librarianChatClient") ChatClient librarianChatClient) {
        this.conversationService = conversationService;
        this.messageService = messageService;

        this.librarianChatClient = librarianChatClient;
    }

    public Message sendMessageInConversation(String conversationId, Message message) {
        Conversation conversation = this.conversationService.findConversationById(conversationId); // first transaction

        message.setConversation(conversation);
        Message createdMessage = this.messageService.createMessage(message); // second transaction

        String responseContent = this.sendMessage(createdMessage.getContent());
        Message response = new Message();
        response.setConversation(conversation);
        response.setContent(responseContent);
        return this.messageService.createMessage(response); // third transaction
    }

    public Message sendMessage(Message message) {
        SendMessageDto sendMessageDto = new SendMessageDto();
        sendMessageDto.setContent(message.getContent());

        String responseContent = this.sendMessage(sendMessageDto);

        Message receivedMessage = new Message();
        receivedMessage.setContent(responseContent);
        return receivedMessage;
    }

    public String sendMessage(SendMessageDto sendMessageDto) {
        return this.sendMessage(sendMessageDto.getContent());
    }

    public String sendMessage(String message) {
        return this.librarianChatClient.prompt()
                .user(message)
                .call()
                .content();
    }
}
