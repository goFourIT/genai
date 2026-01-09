package io.github.go4it.semanticsearch.repository;

import io.github.go4it.semanticsearch.domain.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, String> {
}
