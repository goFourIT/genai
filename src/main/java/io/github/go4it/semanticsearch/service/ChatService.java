package io.github.go4it.semanticsearch.service;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.vectorstore.QuestionAnswerAdvisor;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.stereotype.Service;

@Service
public class ChatService {

    private final ChatClient chatClient;

    private final VectorStore vectorStore;

    public ChatService(ChatClient chatClient, VectorStore vectorStore) {
        this.chatClient = chatClient;
        this.vectorStore = vectorStore;
    }

    public String sendMessage(String message) {
        return this.chatClient.prompt()
                .user(message)
                .call()
                .content();
    }

    public String sendBusinessMessage(String message) {
        return this.chatClient.prompt()
                .advisors(QuestionAnswerAdvisor.builder(vectorStore).build())
                .user(message)
                .call()
                .content();
    }
}
