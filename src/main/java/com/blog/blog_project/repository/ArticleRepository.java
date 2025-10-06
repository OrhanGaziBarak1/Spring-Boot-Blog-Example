package com.blog.blog_project.repository;

import com.blog.blog_project.entity.Article;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;

public interface ArticleRepository extends JpaRepository<Article, String> {
    List<Article> findByAuthorId(long authorId);
    List<Article> findByAuthorIdAndIsDeletedFalse(long authorId);
    List<Article> findByIsDeletedFalse();
    List<Article> findByIsDeletedTrue();

    List<Article> findByCreatedAtBetween(Date startDate, Date endDate);
    List<Article> findByUpdatedAtBetween(Date startDate, Date endDate);
    List<Article> findByDeletedAtBetween(Date startDate, Date endDate);
}
