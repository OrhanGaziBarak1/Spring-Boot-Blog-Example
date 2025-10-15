package com.blog.blog_project.services.impl;

import com.blog.blog_project.dto.ArticleDTO;
import com.blog.blog_project.dto.PagedResponseDTO;
import com.blog.blog_project.entity.Favorite;
import com.blog.blog_project.exception.ArticleNotFoundException;
import com.blog.blog_project.exception.FavoriteshipAlreadyExistException;
import com.blog.blog_project.exception.FavoriteshipNotFoundException;
import com.blog.blog_project.exception.UnauthorizedArticleAccessException;
import com.blog.blog_project.repository.ArticleRepository;
import com.blog.blog_project.repository.FavoriteRepository;
import com.blog.blog_project.services.ArticleService;
import com.blog.blog_project.services.FavoriteService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class FavoriteServiceImpl implements FavoriteService {
    private final FavoriteRepository favoriteRepository;
    private final ArticleRepository articleRepository;

    private final ArticleService articleService;

    @Transactional
    @Override
    public void favorite(UUID userPublicId, String articleId) {
        if (articleRepository.existsByIdAndUserPublicIdAndIsDeletedFalse(articleId, userPublicId)) {
            throw new UnauthorizedArticleAccessException(userPublicId, articleId);
        }

        if (!(articleRepository.existsByIdAndIsDeletedFalse(articleId))) {
            throw new ArticleNotFoundException(articleId);
        }

        if (favoriteRepository.existsByUserPublicIdAndArticleId(userPublicId, articleId)) {
            throw new FavoriteshipAlreadyExistException(articleId);
        }

        Favorite favoritedArticle = new Favorite();

        favoritedArticle.setArticleId(articleId);
        favoritedArticle.setUserPublicId(userPublicId);

        favoriteRepository.save(favoritedArticle);
    }

    @Transactional
    @Override
    public void unfavorite(UUID userPublicId, String articleId) {
        Favorite favoriteship = favoriteRepository.findByUserPublicIdAndArticleId(userPublicId, articleId)
                .orElseThrow(() -> new FavoriteshipNotFoundException(userPublicId, articleId));
        favoriteRepository.delete(favoriteship);

    }

    @Override
    public PagedResponseDTO<ArticleDTO> getFavorites(UUID userPublicId, int page, int size) {
        List<Favorite> favoriteList = favoriteRepository.findByUserPublicId(userPublicId);
        List<String> articleIdList = favoriteList.stream().map(Favorite::getArticleId).toList();
        return articleService.getArticlesById(articleIdList, page, size);
    }
}
