package io.github.go4it.semanticsearch.service;

import io.github.go4it.semanticsearch.domain.entity.Message;
import io.github.go4it.semanticsearch.repository.MessageRepository;
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
