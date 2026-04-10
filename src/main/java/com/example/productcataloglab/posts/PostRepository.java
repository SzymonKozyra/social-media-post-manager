package com.example.productcataloglab.posts;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
    List<Post> findByAuthor(String author);
    List<Post> findByTopic(PostTopic topic);
    List<Post> findByTitleContainingIgnoreCase(String title);
    boolean existsByTitle(String title);
}