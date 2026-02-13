package io.github.go4it.genai.controller;

import io.github.go4it.genai.domain.entity.Message;
import io.github.go4it.genai.service.BookChatService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/librarian")
public class BookChatController {

    private final BookChatService bookChatService;

    public BookChatController(BookChatService bookChatService) {
        this.bookChatService = bookChatService;
    }

    @PostMapping("/chat/send-message-in-conversation/{conversationId}")
    ResponseEntity<Message> sendMessageInConversation(@PathVariable String conversationId, @RequestBody Message message) {
        return ResponseEntity.ok(this.bookChatService.sendMessageInConversation(conversationId, message));
    }

    @PostMapping("/chat/send-message")
    ResponseEntity<Message> sendMessage(@RequestBody Message message) {
        return ResponseEntity.ok(this.bookChatService.sendMessage(message));
    }
}
