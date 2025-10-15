package com.blog.blog_project.exception;

import java.util.UUID;

public class UnauthorizedArticleAccessException extends RuntimeException {
    public UnauthorizedArticleAccessException(String articleId) {
        super("You are not authorized to access article: " + articleId);
    }

    public UnauthorizedArticleAccessException(UUID userPublicId, String articleId) {
        super("You can not favorite your article\narticleId: %s\nuserPublicId: %s".formatted(articleId, userPublicId));
    }
}
