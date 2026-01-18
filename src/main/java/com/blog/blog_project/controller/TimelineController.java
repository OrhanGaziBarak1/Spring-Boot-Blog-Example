package com.blog.blog_project.controller;

import com.blog.blog_project.dto.ArticleDTO;
import com.blog.blog_project.dto.PagedResponseDTO;
import com.blog.blog_project.entity.User;
import com.blog.blog_project.services.TimelineService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/timeline")
public class TimelineController {

    private final TimelineService timelineService;

    @GetMapping()
    public ResponseEntity<PagedResponseDTO<ArticleDTO>> getTimeline(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @AuthenticationPrincipal User currentUser
            ) {
        PagedResponseDTO<ArticleDTO> timeline = timelineService.getTimeline(
                currentUser.getPublicId(), page, size);
        return ResponseEntity.ok(timeline);
    }
}
