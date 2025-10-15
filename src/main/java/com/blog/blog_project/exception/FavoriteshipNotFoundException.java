package com.blog.blog_project.exception;

import java.util.UUID;

public class FavoriteshipNotFoundException extends RuntimeException {
    public FavoriteshipNotFoundException(UUID userPublicId, String articleId) {
        super("This favoriteship is not found.\nuserPublicId: %s\narticleId: %s".formatted(userPublicId, articleId));
    }
}
