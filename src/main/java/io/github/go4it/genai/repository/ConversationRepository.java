package io.github.go4it.genai.repository;

import io.github.go4it.genai.domain.entity.Conversation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConversationRepository extends JpaRepository<Conversation, String> {
}
