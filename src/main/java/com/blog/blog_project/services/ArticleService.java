package com.blog.blog_project.services;

import com.blog.blog_project.dto.ArticleCreateDTO;
import com.blog.blog_project.dto.ArticleDTO;
import jakarta.transaction.Transactional;

import java.util.List;

public interface ArticleService {
    @Transactional
    ArticleDTO create(ArticleCreateDTO request);

    ArticleDTO getOne(String id);

    List<ArticleDTO> getArticlesByAuthor(long authorId);
}
