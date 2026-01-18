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

    @PostMapping()
    public ResponseEntity<ArticleDTO> create (
            @Valid @RequestBody ArticleCreateDTO request) {
        ArticleDTO response = articleService.create(request);
        return ResponseEntity.ok(response);
    }

    @GetMapping()
    public ResponseEntity<PagedResponseDTO<ArticleDTO>> getArticles(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @AuthenticationPrincipal User currentUser) {
        Long userId = currentUser.getId();
        PagedResponseDTO<ArticleDTO> articles = articleService.getArticlesByAuthor(userId, page, size);
        return ResponseEntity.ok(articles);
    }

    @GetMapping("/author/{authorId}")
    public ResponseEntity<PagedResponseDTO<ArticleDTO>> getArticlesByAuthor(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @PathVariable Long authorId) {
        authenticationService.checkAuthor(authorId);
        PagedResponseDTO<ArticleDTO> articles = articleService.getArticlesByAuthor(authorId, page, size);
        return ResponseEntity.ok(articles);
    }

    @GetMapping("/{articleId}")
    public ResponseEntity<ArticleDTO> getOne(@PathVariable String articleId) {
        ArticleDTO response = articleService.getOne(articleId);
        return ResponseEntity.ok(response);
    }

    @PatchMapping("/{articleId}")
    public ResponseEntity<ArticleDTO> updateArticle(
            @PathVariable String articleId,
            @Valid @RequestBody ArticleUpdateDTO request,
            @AuthenticationPrincipal User currentUser
    ) {
        articleService.checkAuthority(currentUser.getId(), articleId);
        ArticleDTO response = articleService.update(request, articleId);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{articleId}")
    public ResponseEntity<Void> delete(
            @PathVariable String articleId,
            @AuthenticationPrincipal User currentUser
    ) {
        articleService.checkAuthority(currentUser.getId(), articleId);
        articleService.delete(articleId);
        return ResponseEntity.ok().build();
    }
}
