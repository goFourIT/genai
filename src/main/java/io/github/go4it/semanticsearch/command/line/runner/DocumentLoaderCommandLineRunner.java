package io.github.go4it.semanticsearch.command.line.runner;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.go4it.semanticsearch.domain.Book;
import org.springframework.ai.document.Document;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public class DocumentLoaderCommandLineRunner implements CommandLineRunner {

    private final VectorStore vectorStore;
    private final Resource booksFile;
    private final ObjectMapper objectMapper;


    public DocumentLoaderCommandLineRunner(
            VectorStore vectorStore,
            @Value("classpath:books.json") Resource booksFile,
            ObjectMapper objectMapper
    ) {
        this.vectorStore = vectorStore;
        this.booksFile = booksFile;
        this.objectMapper = objectMapper;
    }

    @Override
    public void run(String... args) throws Exception {
//        JsonReader jsonReader = new JsonReader(this.booksFile, "description");
//        List<Document> documents = jsonReader.get();
        List<Book> books = this.objectMapper.readValue(this.booksFile.getInputStream(), new TypeReference<List<Book>>() {});
        List<Document> documents = books.stream().map(book -> new Document(
                "Book titled " + book.getTitle() + " written by " + book.getAuthor() + " having " + book.getNumberOfPages() + " pages.\n" + book.getDescription(),
                Map.of("title", book.getTitle(),
                        "author", book.getAuthor(),
                        "numberOfPages", book.getNumberOfPages())
        )).toList();
//        vectorStore.add(documents);
    }
}
