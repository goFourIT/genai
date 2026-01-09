package io.github.go4it.semanticsearch.repository;

import io.github.go4it.semanticsearch.domain.entity.Book;
import org.springframework.data.repository.CrudRepository;

public interface BookRepository extends CrudRepository<Book, String> {
}
