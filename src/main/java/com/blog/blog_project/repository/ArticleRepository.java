package com.blog.blog_project.repository;

import com.blog.blog_project.entity.Article;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ArticleRepository extends JpaRepository<Article, String> {

    Page<Article> findByAuthorIdAndIsDeletedFalse(long authorId, Pageable pageable);
    Optional<Article> findByIdAndIsDeletedFalse(String id);
    boolean existsByIdAndUserPublicIdAndIsDeletedFalse(String id, UUID userPublicId);
    boolean existsByIdAndIsDeletedFalse(String id);

    Page <Article> findByUserPublicIdInAndIsDeletedFalseOrderByCreatedAtDesc(List<UUID> userPublicId, Pageable pageable);
    Page <Article> findByIdInAndIsDeletedFalseOrderByCreatedAtDesc(List <String> articleIdList, Pageable pageable);
}
