package io.github.go4it.semanticsearch.command.line.runner;

import org.springframework.ai.document.Document;
import org.springframework.ai.reader.JsonReader;
import org.springframework.ai.transformer.splitter.TokenTextSplitter;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DocumentLoaderCommandLineRunner implements CommandLineRunner {

    private final VectorStore vectorStore;
    private final Resource booksFile;


    public DocumentLoaderCommandLineRunner(
            VectorStore vectorStore,
            @Value("classpath:books.json") Resource booksFile
    ) {
        this.vectorStore = vectorStore;
        this.booksFile = booksFile;
    }

    @Override
    public void run(String... args) throws Exception {
        JsonReader jsonReader = new JsonReader(this.booksFile, "title", "author", "numberOfPages", "description");
        List<Document> documents = jsonReader.get();

        TokenTextSplitter tokenTextSplitter = new TokenTextSplitter();
        List<Document> transformedDocuments = tokenTextSplitter.apply(documents);

        vectorStore.add(transformedDocuments);
    }
}
