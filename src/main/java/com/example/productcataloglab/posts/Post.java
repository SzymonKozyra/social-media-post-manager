package com.example.productcataloglab.posts;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "posts")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Tytuł posta jest wymagany")
    @Size(min = 5, max = 100, message = "Tytuł musi mieć od 5 do 100 znaków")
    @Column(nullable = false)
    private String title;

    @NotBlank(message = "Treść posta nie może być pusta")
    @Size(max = 1000, message = "Treść może mieć maksymalnie 1000 znaków")
    @Column(nullable = false, columnDefinition = "TEXT")
    private String content;

    @NotBlank(message = "Autor jest wymagany")
    @Column(nullable = false)
    private String author;

    @NotNull(message = "Temat posta jest wymagany")
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private PostTopic topic;

    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }
}