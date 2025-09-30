package com.blog.blog_project.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.blog.blog_project.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional <User> findByEmail(String email);
    boolean existsByEmail(String email);
    Optional <User> findByUsername(String username);

}
