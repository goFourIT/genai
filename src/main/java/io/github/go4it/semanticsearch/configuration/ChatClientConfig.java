package io.github.go4it.semanticsearch.configuration;

import io.github.go4it.semanticsearch.advisor.LogAdvisor;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.vectorstore.QuestionAnswerAdvisor;
import org.springframework.ai.vectorstore.SearchRequest;
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
                   \n
                   You are a librarian. Offer suggestions to the customers based on your list of books. Do not mention
                   anything about database and play your role as a librarian.
                   \n
                   Do not suggest anything outside of the books you are provided. Do not mention anything about the
                   information provided in the context. Follow the user's question.
                  """)
                .defaultAdvisors(List.of(
                        new LogAdvisor(),
                        QuestionAnswerAdvisor.builder(vectorStore).searchRequest(
                                SearchRequest.builder()
                                        .similarityThreshold(0.6) // the minimum distance required between your query vector and vectors in database
                                        .topK(20) // the number of vectors to be returned
                                        .build()
                        ).build())
                )
                .build();
    }
}