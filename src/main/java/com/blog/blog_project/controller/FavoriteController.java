package com.blog.blog_project.controller;

import com.blog.blog_project.dto.ArticleDTO;
import com.blog.blog_project.dto.PagedResponseDTO;
import com.blog.blog_project.entity.User;
import com.blog.blog_project.services.FavoriteService;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/favorite")
@RequiredArgsConstructor
public class FavoriteController {

    private final FavoriteService favoriteService;

    @PostMapping("/{articleId}")
    public ResponseEntity<?> favorite(
            @PathVariable String articleId,
            @AuthenticationPrincipal User currentUser
    ) {
        favoriteService.favorite(currentUser.getPublicId(), articleId);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{articleId}")
    public  ResponseEntity<?> unfavorite(
            @PathVariable String articleId,
            @AuthenticationPrincipal User currentUser
    ) {
        favoriteService.unfavorite(currentUser.getPublicId(), articleId);
        return ResponseEntity.ok().build();
    }

    @GetMapping()
    public ResponseEntity<?> getFavorites(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @AuthenticationPrincipal User currentUser
    ) {
        PagedResponseDTO<ArticleDTO> favorites = favoriteService.getFavorites(currentUser.getPublicId(), page, size);
        return ResponseEntity.ok(favorites);
    }

}
