package com.blog.blog_project.services;

import com.blog.blog_project.dto.ArticleCreateDTO;
import com.blog.blog_project.dto.ArticleDTO;
import com.blog.blog_project.dto.ArticleUpdateDTO;
import jakarta.transaction.Transactional;

import java.util.List;

public interface ArticleService {
    @Transactional
    ArticleDTO create(ArticleCreateDTO request);

    @Transactional
    ArticleDTO update(ArticleUpdateDTO request, String id);

    @Transactional
    void delete(String id);

    ArticleDTO getOne(String id);

    List<ArticleDTO> getArticlesByAuthor(long authorId);

    void checkAuthority(Long userId, String articleId);
}
