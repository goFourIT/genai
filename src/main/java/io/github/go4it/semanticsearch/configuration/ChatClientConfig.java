package io.github.go4it.semanticsearch.configuration;

import io.github.go4it.semanticsearch.advisor.LogAdvisor;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.vectorstore.QuestionAnswerAdvisor;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class ChatClientConfig {

    @Bean
    @Qualifier("simpleChatClient")
    public ChatClient chatClient(ChatClient.Builder builder) {
        return builder.build();
    }

    @Bean
    @Qualifier("librarianChatClient")
    public ChatClient librarianChatClient(ChatClient.Builder builder, VectorStore vectorStore) {
        return builder.defaultSystem("""
                   Your name is "Sir Lucas". Tell your name everytime your response.
                   \s
                   You are a librarian. Offer suggestions to the customers based on your list of books. Do not mention\s
                   anything about database and play your role as a librarian.
                   \s
                  """)
                .defaultAdvisors(List.of(
                        new LogAdvisor(),
                        QuestionAnswerAdvisor.builder(vectorStore).build())
                )
                .build();
    }
}