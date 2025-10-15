package com.blog.blog_project.controller;

import com.blog.blog_project.dto.ArticleCreateDTO;
import com.blog.blog_project.dto.ArticleDTO;
import com.blog.blog_project.dto.ArticleUpdateDTO;
import com.blog.blog_project.dto.PagedResponseDTO;
import com.blog.blog_project.entity.User;
import com.blog.blog_project.services.ArticleService;
import com.blog.blog_project.services.AuthenticationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/article")
@RequiredArgsConstructor
public class ArticleController {

    private final ArticleService articleService;
    private final AuthenticationService authenticationService;

    @PostMapping("/create")
    public ResponseEntity<?> create (
            @Valid @RequestBody ArticleCreateDTO request,
            @AuthenticationPrincipal User currentUser) {
        ArticleDTO response = articleService.create(request, currentUser.getPublicId());
        return ResponseEntity.ok(response);
    }

    @GetMapping("/get-articles")
    public ResponseEntity<?> getArticles(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @AuthenticationPrincipal User currentUser) {
        Long userId = currentUser.getId();
        PagedResponseDTO<ArticleDTO> articles = articleService.getArticlesByAuthor(userId, page, size);
        return ResponseEntity.ok(articles);
    }

    @GetMapping("/get-articles/{id}")
    public ResponseEntity<?> getArticlesByAuthor(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @PathVariable Long id) {
        authenticationService.checkAuthor(id);
        PagedResponseDTO<ArticleDTO> articles = articleService.getArticlesByAuthor(id, page, size);
        return ResponseEntity.ok(articles);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getOne(@PathVariable String id) {
        ArticleDTO response = articleService.getOne(id);
        return ResponseEntity.ok(response);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<?> updateArticle(
            @PathVariable String id,
            @Valid @RequestBody ArticleUpdateDTO request,
            @AuthenticationPrincipal User currentUser
    ) {
        articleService.checkAuthority(currentUser.getId(), id);
        ArticleDTO response = articleService.update(request, id);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(
            @PathVariable String id,
            @AuthenticationPrincipal User currentUser
    ) {
        articleService.checkAuthority(currentUser.getId(), id);
        articleService.delete(id);
        return ResponseEntity.ok().build();
    }
}
