package com.blog.blog_project.services;

import com.blog.blog_project.dto.ArticleCreateDTO;
import com.blog.blog_project.dto.ArticleDTO;
import com.blog.blog_project.dto.ArticleUpdateDTO;
import com.blog.blog_project.dto.PagedResponseDTO;
import jakarta.transaction.Transactional;

import java.util.List;
import java.util.UUID;

public interface ArticleService {
    @Transactional
    ArticleDTO create(ArticleCreateDTO request, UUID userPublicId);

    @Transactional
    ArticleDTO update(ArticleUpdateDTO request, String id);

    @Transactional
    void delete(String id);

    ArticleDTO getOne(String id);

    PagedResponseDTO<ArticleDTO> getArticlesByAuthor(long authorId, int page, int size);
    PagedResponseDTO<ArticleDTO> getArticlesByAuthor(List<UUID> userPublicId, int page, int size);
    PagedResponseDTO<ArticleDTO> getArticlesById(List<String> articleIdList, int page, int size);

    void checkAuthority(Long userId, String articleId);
}
