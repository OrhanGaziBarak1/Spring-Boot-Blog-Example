package com.blog.blog_project.exception;

public class AuthorNotFoundException extends RuntimeException{
    public AuthorNotFoundException(Long id) {
        super("Author not found: " + id);
    }

    public AuthorNotFoundException(String email) {
        super("Author not found: " + email);
    }
}
