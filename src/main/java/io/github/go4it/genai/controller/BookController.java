package io.github.go4it.genai.controller;

import io.github.go4it.genai.domain.entity.Book;
import io.github.go4it.genai.service.BookService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/books")
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping
    ResponseEntity<List<Book>> findAllBooks() {
        return ResponseEntity.ok(this.bookService.findAllBooks());
    }

    @GetMapping("/{bookId}")
    ResponseEntity<Book> findBookById(@PathVariable String bookId) {
        return ResponseEntity.ok(this.bookService.findBookById(bookId));
    }

    @PostMapping
    ResponseEntity<Void> createBook(@RequestBody Book book) {
        this.bookService.createBook(book);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{bookId}")
    ResponseEntity<Void> updateBook(@PathVariable String bookId, @RequestBody Book book) {
        this.bookService.updateBook(book);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{bookId}")
    ResponseEntity<Void> deleteBook(@PathVariable String bookId) {
        this.bookService.deleteBookById(bookId);
        return ResponseEntity.noContent().build();
    }
}
