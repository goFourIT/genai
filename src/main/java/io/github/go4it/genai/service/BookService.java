package io.github.go4it.genai.service;

import io.github.go4it.genai.domain.entity.Book;
import io.github.go4it.genai.repository.BookRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {

    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public List<Book> findAllBooks() {
        return this.bookRepository.findAll();
    }

    public Book findBookById(String id) {
        return this.bookRepository.findById(id).orElseThrow();
    }

    public void createBook(Book book) {
        this.bookRepository.save(book);
    }

    public void updateBook(Book book) {

        this.bookRepository.save(book);
    }

    public void deleteBookById(String id) {
        this.bookRepository.deleteById(id);
    }
}
