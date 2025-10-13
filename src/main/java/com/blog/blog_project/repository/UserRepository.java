package com.blog.blog_project.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.blog.blog_project.entity.User;

public interface UserRepository extends JpaRepository<User, String> {
    Optional <User> findByEmail(String email);
    Optional <User> findByUsername(String username);
    Optional <User> findById(Long id);

    boolean existsByEmail(String email);
    boolean existsById(Long id);

    boolean existsByPublicId(UUID followedUserPublicId);
}
