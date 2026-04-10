package com.example.productcataloglab.posts;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class PostAlreadyExistsException extends RuntimeException {
    public PostAlreadyExistsException(String message) {
        super(message);
    }
}