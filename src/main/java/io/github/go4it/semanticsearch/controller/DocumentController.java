package io.github.go4it.semanticsearch.controller;

import io.github.go4it.semanticsearch.service.DocumentService;
import org.springframework.ai.document.Document;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/documents")
public class DocumentController {

    private final DocumentService documentService;

    public DocumentController(DocumentService documentService) {
        this.documentService = documentService;
    }

    @PostMapping("/store")
    ResponseEntity<Void> store(@RequestBody String text) {
        this.documentService.store(text);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/query")
    ResponseEntity<List<Document>> query(@RequestBody String query) {
        return ResponseEntity.ok(this.documentService.search(query));
    }
}
