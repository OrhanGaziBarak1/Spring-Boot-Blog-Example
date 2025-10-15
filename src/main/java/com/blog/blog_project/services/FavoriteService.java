package com.blog.blog_project.services;

import com.blog.blog_project.dto.ArticleDTO;
import com.blog.blog_project.dto.PagedResponseDTO;

import java.util.UUID;

public interface FavoriteService {
    void favorite(UUID userPublicId, String articleId);
    void unfavorite(UUID userPublicId, String articleId);

    PagedResponseDTO<ArticleDTO> getFavorites(UUID userPublicId, int page, int size);
}
