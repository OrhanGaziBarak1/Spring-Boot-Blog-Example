package com.blog.blog_project.repository;

import com.blog.blog_project.entity.Clap;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;
import java.util.UUID;

public interface ClapRepository extends JpaRepository<Clap, Long> {
    boolean existsByUserPublicIdAndArticleId(UUID userPublicId, String articleId);
    Optional <Clap> findByUserPublicIdAndArticleId(UUID userPublicId, String articleId);

    @Query("SELECT SUM(c.clapCount) FROM Clap c WHERE c.articleId = :articleId")
    Long getTotalClapsForArticle(@Param("articleId") String articleId);
}
