package io.github.go4it.genai.repository;

import io.github.go4it.genai.domain.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, String> {
}
