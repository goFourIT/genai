package io.github.go4it.genai.repository;

import io.github.go4it.genai.domain.entity.Message;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MessageRepository extends JpaRepository<Message, String> {
}
