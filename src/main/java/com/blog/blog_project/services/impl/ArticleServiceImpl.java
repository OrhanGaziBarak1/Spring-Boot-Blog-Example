package com.blog.blog_project.services.impl;

import com.blog.blog_project.dto.ArticleCreateDTO;
import com.blog.blog_project.dto.ArticleDTO;
import com.blog.blog_project.dto.ArticleUpdateDTO;
import com.blog.blog_project.dto.PagedResponseDTO;
import com.blog.blog_project.entity.Article;
import com.blog.blog_project.exception.ArticleNotFoundException;
import com.blog.blog_project.exception.UnauthorizedArticleAccessException;
import com.blog.blog_project.mapper.ArticleMapper;
import com.blog.blog_project.repository.ArticleRepository;
import com.blog.blog_project.repository.UserRepository;
import com.blog.blog_project.services.ArticleService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ArticleServiceImpl implements ArticleService {

    private final ArticleRepository articleRepository;
    private final UserRepository userRepository;
    private final ArticleMapper articleMapper;

    @Transactional
    @Override
    public ArticleDTO create(ArticleCreateDTO request, UUID userPublicId) {
        Article article = new Article();

        article.setContent(request.getContent());
        article.setAuthorId(request.getAuthorId());
        article.setCreatedAt(new Date());
        article.setUserPublicId(userPublicId);

        Article savedArticle = articleRepository.save(article);

        return articleMapper.toDTO(savedArticle);
    }

    @Override
    public ArticleDTO getOne(String id) {
        return articleRepository.findByIdAndIsDeletedFalse(id)
                .map(articleMapper::toDTO)
                .orElseThrow(() -> new ArticleNotFoundException(id));
    }

    @Override
    public PagedResponseDTO<ArticleDTO> getArticlesByAuthor(long authorId, int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("createdAt").descending());

        Page<Article> articles = articleRepository.findByAuthorIdAndIsDeletedFalse(authorId, pageable);

        List<ArticleDTO> articleDTOList = articleMapper.toDTOList(articles.getContent());

        return PagedResponseDTO.from(articles, articleDTOList);
    }

    @Override
    public PagedResponseDTO<ArticleDTO> getArticlesByAuthor(List<UUID> userPublicIdList, int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("createdAt").descending());
        Page <Article> articles = articleRepository.findByUserPublicIdInAndIsDeletedFalseOrderByCreatedAtDesc(userPublicIdList, pageable);
        List <ArticleDTO> articleDTOS = articleMapper.toDTOList(articles.getContent());
        return PagedResponseDTO.from(articles, articleDTOS);
    }

    @Override
    public PagedResponseDTO<ArticleDTO> getArticlesById(List<String> articleIdList, int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("createdAt").descending());
        Page <Article> articles = articleRepository.findByIdInAndIsDeletedFalseOrderByCreatedAtDesc(articleIdList, pageable);
        List <ArticleDTO> articleDTOS = articleMapper.toDTOList(articles.getContent());
        return PagedResponseDTO.from(articles, articleDTOS);
    }

    @Transactional
    @Override
    public ArticleDTO update(ArticleUpdateDTO request, String id) {
        Article article = articleRepository.findById(id)
                .orElseThrow(() -> new ArticleNotFoundException(id));

        article.setContent(request.getContent());
        article.setUpdatedAt(new Date());

        Article updatedArticle = articleRepository.save(article);

        return articleMapper.toDTO(updatedArticle);
    }

    @Transactional
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
