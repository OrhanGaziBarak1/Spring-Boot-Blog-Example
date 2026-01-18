package com.blog.blog_project.controller;

import com.blog.blog_project.entity.User;
import com.blog.blog_project.services.FollowService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/follow")
@RequiredArgsConstructor
public class FollowController {
    private final FollowService followService;

    @PostMapping("/{publicId}")
    public ResponseEntity<Void> follow(
            @PathVariable UUID publicId,
            @AuthenticationPrincipal User currentUser
            ) {
        followService.follow(currentUser.getPublicId(), publicId);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{publicId}")
    public ResponseEntity<Void> unfollow(
            @PathVariable UUID publicId,
            @AuthenticationPrincipal User currentUser
    ) {
        followService.unfollow(currentUser.getPublicId(), publicId);
        return ResponseEntity.ok().build();
    }
}
