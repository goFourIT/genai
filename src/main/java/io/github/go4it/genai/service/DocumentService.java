package io.github.go4it.genai.service;

import org.springframework.ai.document.Document;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DocumentService {

    private final VectorStore vectorStore;

    public DocumentService(VectorStore vectorStore) {
        this.vectorStore = vectorStore;
    }

    public void store(String text) {
        Document document = new Document(text);
        vectorStore.add(List.of(document));
    }

    public List<Document> search(String query) {
        return vectorStore.similaritySearch(query);
    }
}
