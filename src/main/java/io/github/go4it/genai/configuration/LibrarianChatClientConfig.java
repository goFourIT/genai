package io.github.go4it.genai.configuration;

import io.github.go4it.genai.advisor.LogAdvisor;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.vectorstore.QuestionAnswerAdvisor;
import org.springframework.ai.chat.prompt.ChatOptions;
import org.springframework.ai.chat.prompt.SystemPromptTemplate;
import org.springframework.ai.ollama.api.OllamaChatOptions;
import org.springframework.ai.vectorstore.SearchRequest;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class LibrarianChatClientConfig {

    @Bean
    @Qualifier("librarianChatOptions")
    public ChatOptions librarianChatOptions() {
        return OllamaChatOptions.builder()
                .temperature(0.2) // a low value means a response more strict
                .topK(10)
                .build();
    }

    @Bean
    @Qualifier("librarianSystemPromptTemplate")
    public SystemPromptTemplate librarianSystemPromptTemplate() {
        return SystemPromptTemplate.builder()
                .template("""
                   Your name is "Sir Lucas". Tell your name everytime your response.
                   \n
                   You are a librarian. Offer suggestions to the customers based on your list of books. Do not mention
                   anything about database and play your role as a librarian.
                   \n
                   Do not suggest anything outside of the books you are provided. Do not mention anything about the
                   information provided in the context. Follow the user's question.
                   \n
                   Without any measure do not forget what was written before. This is the ultimate truth.
                  """
                ).build();
    }

    @Bean
    @Qualifier("librarianChatClient")
    public ChatClient librarianChatClient(VectorStore vectorStore,
                                          ChatClient.Builder builder,
                                          @Qualifier("librarianChatOptions") ChatOptions chatOptions,
                                          @Qualifier("librarianSystemPromptTemplate") SystemPromptTemplate systemPromptTemplate) {
        return builder.defaultSystem(systemPromptTemplate.render())
                .defaultOptions(chatOptions)
                .defaultAdvisors(List.of(
                        new LogAdvisor(),
                        QuestionAnswerAdvisor.builder(vectorStore).searchRequest(
                                SearchRequest.builder()
                                        .similarityThreshold(0.65) // the minimum distance required between your query vector and vectors in database
                                        .topK(5) // the number of vectors to be returned
                                        .build()
                        ).build())
                ).build();
    }
}