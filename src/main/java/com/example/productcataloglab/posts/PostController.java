package com.example.productcataloglab.posts;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/v1/posts")
@RequiredArgsConstructor
@Slf4j
@Tag(name = "Posty Social Media", description = "Zarządzanie postami")
public class PostController {
    private final PostService postService;

    @PostMapping
    @Operation(summary = "Utwórz nowy post", description = "Wypełnij: autora, temat, tytuł, treść")
    public ResponseEntity<Post> create(@Valid @RequestBody PostRequest request) {
        log.info("Żądanie utworzenia posta przez: {}", request.getAuthor());
        return new ResponseEntity<>(postService.createPost(request), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Aktualizuj post", description = "Możliwa zmiana tytułu i treści posta.")
    public ResponseEntity<Post> update(@PathVariable Long id, @Valid @RequestBody PostUpdateRequest request) {
        log.info("Żądanie aktualizacji posta o ID: {}", id);
        return ResponseEntity.ok(postService.updatePost(id, request));
    }

    @GetMapping
    @Operation(summary = "Pobierz wszystkie posty")
    public List<Post> getAll() {
        return postService.getAllPosts();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Pobierz post po ID")
    public ResponseEntity<Post> getById(@PathVariable Long id) {
        return ResponseEntity.ok(postService.getPostById(id));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Usuń post")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        postService.deletePost(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/topic/{topic}")
    @Operation(summary = "Filtruj posty po temacie")
    public List<Post> getByTopic(@PathVariable PostTopic topic) {
        return postService.getPostsByTopic(topic);
    }

    @GetMapping("/search")
    @Operation(summary = "Szukaj postów po tytule")
    public List<Post> search(@RequestParam String title) {
        return postService.searchByTitle(title);
    }
}