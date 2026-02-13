package io.github.go4it.genai.controller;

import io.github.go4it.genai.domain.entity.Conversation;
import io.github.go4it.genai.service.ConversationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/conversations")
public class ConversationController {

    private final ConversationService conversationService;

    public ConversationController(ConversationService conversationService) {
        this.conversationService = conversationService;
    }

    @GetMapping
    ResponseEntity<List<Conversation>> findAllConversations() {
        return ResponseEntity.ok(this.conversationService.findAllConversations());
    }

    @GetMapping("/{conversationId}")
    ResponseEntity<Conversation> findConversationById(@PathVariable String conversationId) {
        return null;
    }

    @PostMapping
    ResponseEntity<Conversation> createConversation(@RequestBody Conversation conversation) {
        return ResponseEntity.ok(this.conversationService.createConversation(conversation));
    }
}
