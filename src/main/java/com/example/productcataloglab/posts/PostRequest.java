package com.example.productcataloglab.posts;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import jakarta.validation.constraints.*;
import lombok.Data;

@Data
@JsonPropertyOrder({ "author", "topic", "title", "content" })
public class PostRequest {

    @NotBlank(message = "Autor jest wymagany")
    private String author;

    @NotNull(message = "Temat posta jest wymagany")
    private PostTopic topic;

    @NotBlank(message = "Tytuł posta jest wymagany")
    @Size(min = 5, max = 100, message = "Tytuł musi mieć od 5 do 100 znaków")
    private String title;

    @NotBlank(message = "Treść posta nie może być pusta")
    @Size(max = 1000, message = "Treść może mieć maksymalnie 1000 znaków")
    private String content;
}