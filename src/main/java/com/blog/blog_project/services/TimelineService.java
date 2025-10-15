package com.blog.blog_project.services;

import com.blog.blog_project.dto.ArticleDTO;
import com.blog.blog_project.dto.PagedResponseDTO;

import java.util.UUID;

public interface TimelineService {
    PagedResponseDTO<ArticleDTO> getTimeline(UUID userPublicId, int page, int size);
}
