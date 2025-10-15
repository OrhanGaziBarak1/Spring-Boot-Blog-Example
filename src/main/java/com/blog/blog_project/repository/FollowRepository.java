package com.blog.blog_project.repository;

import com.blog.blog_project.entity.Follow;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface FollowRepository extends JpaRepository<Follow, Long> {
    Optional <Follow> findByUserPublicIdAndFollowedUserPublicId(
            UUID userPublicId, UUID followedUserPublicId
    );

    boolean existsByUserPublicIdAndFollowedUserPublicId(
            UUID userPublicId,
            UUID followedUserPublicId
    );

    List<Follow> findByUserPublicId(UUID userPublicId);
}
