package io.github.go4it.semanticsearch.repository;

import io.github.go4it.semanticsearch.domain.entity.Conversation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConversationRepository extends JpaRepository<Conversation, String> {
}
