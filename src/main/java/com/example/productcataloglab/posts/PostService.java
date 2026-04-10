package com.example.productcataloglab.posts;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class PostService {
    private final PostRepository postRepository;

    public Post createPost(PostRequest request) {
        if (postRepository.existsByTitle(request.getTitle())) {
            throw new RuntimeException("Post o takim tytule już istnieje.");
        }
        Post post = Post.builder()
                .author(request.getAuthor())
                .topic(request.getTopic())
                .title(request.getTitle())
                .content(request.getContent())
                .build();
        return postRepository.save(post);
    }

    public Post updatePost(Long id, PostUpdateRequest request) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Post o ID " + id + " nie istnieje"));

        post.setTitle(request.getTitle());
        post.setContent(request.getContent());

        log.info("Zaktualizowano post o ID: {}.", id);
        return postRepository.save(post);
    }

    public List<Post> getAllPosts() {
        return postRepository.findAll();
    }

    public Post getPostById(Long id) {
        return postRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Post o ID " + id + " nie istnieje"));
    }

    public void deletePost(Long id) {
        if (!postRepository.existsById(id)) {
            throw new RuntimeException("Nie można usunąć - post o ID " + id + " nie istnieje");
        }
        postRepository.deleteById(id);
    }

    public List<Post> getPostsByTopic(PostTopic topic) {
        return postRepository.findByTopic(topic);
    }

    public List<Post> searchByTitle(String title) {
        return postRepository.findByTitleContainingIgnoreCase(title);
    }
}