package io.github.go4it.semanticsearch.controller;

import io.github.go4it.semanticsearch.domain.entity.Conversation;
import io.github.go4it.semanticsearch.domain.entity.Message;
import io.github.go4it.semanticsearch.service.BookChatService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/librarian")
public class BookChatController {

    private final BookChatService bookChatService;

    public BookChatController(BookChatService bookChatService) {
        this.bookChatService = bookChatService;
    }


    @PostMapping("/chat")
    ResponseEntity<Void> createConversation(@RequestBody Conversation conversation) {
        return null;
    }

    @PostMapping("/chat/{conversationId}")
    ResponseEntity<Message> createMessageInConversation(@PathVariable String conversationId, @RequestBody Message message) {
        return null;
    }

    @PostMapping("/chat/send-message")
    ResponseEntity<Message> sendMessage(@RequestBody Message message) {
        return ResponseEntity.ok(this.bookChatService.sendMessage(message));
    }
}
