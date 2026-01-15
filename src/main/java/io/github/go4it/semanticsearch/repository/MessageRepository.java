package io.github.go4it.semanticsearch.repository;

import io.github.go4it.semanticsearch.domain.entity.Message;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MessageRepository extends JpaRepository<Message, String> {
}
