package io.github.go4it.genai.service;

import io.github.go4it.genai.domain.entity.Message;
import io.github.go4it.genai.repository.MessageRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class MessageService {

    private final MessageRepository messageRepository;

    public MessageService(
            MessageRepository messageRepository
    ) {
        this.messageRepository = messageRepository;
    }

    public Message createMessage(Message message) {
        return this.messageRepository.save(message);
    }
}
