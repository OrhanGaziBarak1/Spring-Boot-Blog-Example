package com.blog.blog_project.exception;

import java.util.UUID;

public class ClapNotFoundException extends RuntimeException {
    public ClapNotFoundException(UUID userPublicId, String articleId) {
        super("Clap not found with\nuserPublicId: %s\narticleId: %s".formatted(userPublicId, articleId));
    }
}
