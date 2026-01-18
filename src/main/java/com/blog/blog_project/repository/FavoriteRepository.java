package com.blog.blog_project.repository;

import com.blog.blog_project.entity.Favorite;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface FavoriteRepository extends JpaRepository<Favorite, Long> {
    boolean existsByUserPublicIdAndArticleId(UUID userPublicId, String articleId);
    Optional<Favorite> findByUserPublicIdAndArticleId(UUID userPublicId, String articleId);

    List<Favorite> findByUserPublicId(UUID userPublicId);
}
