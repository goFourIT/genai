package io.github.go4it.semanticsearch.configuration;

import io.github.go4it.semanticsearch.advisor.LogAdvisor;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class AppConfig {

    @Bean
    public ChatClient chatClient(ChatClient.Builder builder) {
        return builder
                .defaultSystem("You help people find books")
                .defaultAdvisors(List.of(new LogAdvisor()))
                .build();
    }
}