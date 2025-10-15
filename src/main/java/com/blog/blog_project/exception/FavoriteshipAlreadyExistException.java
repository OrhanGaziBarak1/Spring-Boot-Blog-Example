package com.blog.blog_project.exception;

public class FavoriteshipAlreadyExistException extends RuntimeException {
    public FavoriteshipAlreadyExistException(String id) {
        super("You are already favorite this article: %s".formatted(id));
    }
}
