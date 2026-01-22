package io.github.go4it.semanticsearch.configuration;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationConfig {

    @Bean
    @Qualifier("simpleChatClient")
    public ChatClient chatClient(ChatClient.Builder builder) {
        return builder.build();
    }
}
