package com.blog.blog_project.services.impl;

import com.blog.blog_project.dto.ArticleDTO;
import com.blog.blog_project.dto.PagedResponseDTO;
import com.blog.blog_project.services.ArticleService;
import com.blog.blog_project.services.FollowService;
import com.blog.blog_project.services.TimelineService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TimelineServiceImpl implements TimelineService {

    private final FollowService followService;
    private final ArticleService articleService;

    @Override
    public PagedResponseDTO<ArticleDTO> getTimeline(UUID userPublicId, int page, int size) {
        List<UUID> followList = followService.getFollowedUsers(userPublicId);
        return articleService.getArticlesByAuthor(followList, page, size);
    }
}
