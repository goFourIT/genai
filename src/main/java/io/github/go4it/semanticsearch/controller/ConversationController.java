package io.github.go4it.semanticsearch.controller;

import io.github.go4it.semanticsearch.domain.entity.Conversation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/conversation")
public class ConversationController {

    @GetMapping
    ResponseEntity<Conversation> findConversationById(@PathVariable String conversationId) {
        return null;
    }
}
