package com.blog.blog_project.exception;

import java.util.UUID;

public class AuthorNotFoundException extends RuntimeException{
    public AuthorNotFoundException(Long id) {
        super("Author not found: " + id);
    }

    public AuthorNotFoundException(String email) {
        super("Author not found: " + email);
    }

    public AuthorNotFoundException(UUID publicUserId) {
        super("Author not found: " + publicUserId);
    }
}
