package com.blog.blog_project.exception;

public class UnauthorizedArticleAccessException extends RuntimeException {
    public UnauthorizedArticleAccessException(String articleId) {
        super("You are not authorized to access article: " + articleId);
    }
}
