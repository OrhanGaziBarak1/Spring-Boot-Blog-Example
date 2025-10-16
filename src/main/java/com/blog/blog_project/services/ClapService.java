package com.blog.blog_project.services;

import com.blog.blog_project.dto.ClapDTO;
import jakarta.transaction.Transactional;

import java.util.UUID;

public interface ClapService {
    @Transactional
    void clap (ClapDTO request, UUID userPublicId, String articleId);

    @Transactional
    void deleteClap (UUID userPublicId, String articleId);
}
