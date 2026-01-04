package io.github.go4it.semanticsearch.controller;

import io.github.go4it.semanticsearch.service.ChatService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/chat")
public class ChatController {

    private final ChatService chatService;

    public ChatController(ChatService chatService) {
        this.chatService = chatService;
    }

    @PostMapping("/send-message")
    ResponseEntity<String> sendMessage(@RequestBody String message) {
        return ResponseEntity.ok(this.chatService.sendMessage(message));
    }

    @PostMapping("/send-business-message")
    ResponseEntity<String> sendBusinessMessage(@RequestBody String message) {
        return ResponseEntity.ok(this.chatService.sendBusinessMessage(message));
    }
}
