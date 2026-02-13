package io.github.go4it.genai.advisor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ai.chat.client.ChatClientRequest;
import org.springframework.ai.chat.client.ChatClientResponse;
import org.springframework.ai.chat.client.advisor.api.CallAdvisor;
import org.springframework.ai.chat.client.advisor.api.CallAdvisorChain;
import org.springframework.core.Ordered;

public class LogAdvisor implements CallAdvisor {

    private static final Logger logger = LoggerFactory.getLogger(LogAdvisor.class);

    @Override
    public ChatClientResponse adviseCall(ChatClientRequest chatClientRequest, CallAdvisorChain callAdvisorChain) {
        this.logRequest(chatClientRequest);

        ChatClientResponse chatClientResponse = callAdvisorChain.nextCall(chatClientRequest);

        this.logResponse(chatClientResponse);

        return chatClientResponse;
    }

    @Override
    public String getName() {
        return this.getClass().getSimpleName();
    }

    @Override
    public int getOrder() {
        return Ordered.LOWEST_PRECEDENCE - 1;
    }

    private void logRequest(ChatClientRequest request) {
        logger.debug("----- REQUEST IS -----");
        logger.debug(request.prompt().getContents());
    }

    private void logResponse(ChatClientResponse response) {
        logger.debug("----- RESPONSE IS -----");
        logger.debug(response.chatResponse().toString());
    }
}
