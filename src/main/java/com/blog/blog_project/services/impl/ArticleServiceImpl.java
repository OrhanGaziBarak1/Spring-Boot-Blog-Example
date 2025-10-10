package com.blog.blog_project.services.impl;

import com.blog.blog_project.dto.ArticleCreateDTO;
import com.blog.blog_project.dto.ArticleDTO;
import com.blog.blog_project.dto.ArticleUpdateDTO;
import com.blog.blog_project.entity.Article;
import com.blog.blog_project.exception.ArticleNotFoundException;
import com.blog.blog_project.exception.UnauthorizedArticleAccessException;
import com.blog.blog_project.repository.ArticleRepository;
import com.blog.blog_project.repository.UserRepository;
import com.blog.blog_project.services.ArticleService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ArticleServiceImpl implements ArticleService {

    private final ArticleRepository articleRepository;
    private final UserRepository userRepository;

    @Transactional
    @Override
    public ArticleDTO create(ArticleCreateDTO request) {
        Article article = new Article();

        article.setContent(request.getContent());
        article.setAuthorId(request.getAuthorId());
        article.setCreatedAt(new Date());

        Article savedArticle = articleRepository.save(article);

        return new ArticleDTO(
                savedArticle.getContent(),
                savedArticle.getId(),
                savedArticle.getCreatedAt(),
                savedArticle.getUpdatedAt(),
                savedArticle.getAuthorId()
        );
    }

    @Override
    public ArticleDTO getOne(String id) {
        return articleRepository.findByIdAndIsDeletedFalse(id)
                .map(article -> new ArticleDTO(
                        article.getContent(),
                        article.getId(),
                        article.getCreatedAt(),
                        article.getUpdatedAt(),
                        article.getAuthorId()
                ))
                .orElseThrow(() -> new ArticleNotFoundException(id));
    }

    @Override
    public List<ArticleDTO> getArticlesByAuthor(long authorId) {
        List<Article> articles = articleRepository.findByAuthorIdAndIsDeletedFalse(authorId);
        return articles.stream().map(article -> new ArticleDTO(
                article.getContent(),
                article.getId(),
                article.getCreatedAt(),
                article.getUpdatedAt(),
                article.getAuthorId()
                )).collect(Collectors.toList());
    }

    @Transactional
    @Override
    public ArticleDTO update(ArticleUpdateDTO request, String id) {
        Article article = articleRepository.findById(id)
                .orElseThrow(() -> new ArticleNotFoundException(id));

        article.setContent(request.getContent());
        article.setUpdatedAt(new Date());

        Article updatedArticle = articleRepository.save(article);

        return new ArticleDTO(
                updatedArticle.getContent(),
                updatedArticle.getId(),
                updatedArticle.getCreatedAt(),
                updatedArticle.getUpdatedAt(),
                updatedArticle.getAuthorId()
        );
    }

    @Override
    public void delete(String id) {
        Article article = articleRepository.findById(id)
                .orElseThrow(()->new ArticleNotFoundException(id));

        article.setDeleted(true);
        article.setDeletedAt(new Date());

        articleRepository.save(article);
    }

    @Override
    public void checkAuthority(Long userId, String articleId) {
        Article article = articleRepository.findById(articleId)
                .orElseThrow(() -> new ArticleNotFoundException(articleId));
        if (article.getAuthorId() != userId) throw new UnauthorizedArticleAccessException(articleId);
    }

}
