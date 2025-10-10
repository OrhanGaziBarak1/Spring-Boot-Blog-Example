package com.blog.blog_project.repository;

import com.blog.blog_project.entity.Article;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface ArticleRepository extends JpaRepository<Article, String> {
    Page<Article> findByAuthorId(long authorId, Pageable pageable);
    Page<Article> findByAuthorIdAndIsDeletedFalse(long authorId, Pageable pageable);
    List<Article> findByIsDeletedFalse();
    List<Article> findByIsDeletedTrue();

    List<Article> findByCreatedAtBetween(Date startDate, Date endDate);
    List<Article> findByUpdatedAtBetween(Date startDate, Date endDate);
    List<Article> findByDeletedAtBetween(Date startDate, Date endDate);

    Optional<Article> findByIdAndIsDeletedFalse(String id);
}
