package com.blog.blog_project.controller;

import com.blog.blog_project.dto.ArticleCreateDTO;
import com.blog.blog_project.dto.ArticleDTO;
import com.blog.blog_project.entity.User;
import com.blog.blog_project.services.ArticleService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/article")
@RequiredArgsConstructor
public class ArticleController {

    private final ArticleService articleService;

    @PostMapping("/create")
    public ResponseEntity<?> create (@Valid @RequestBody ArticleCreateDTO request) {
        try{
            ArticleDTO response = articleService.create(request);
            return ResponseEntity.ok(response);
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
        }
    }

    @GetMapping("/")
    public ResponseEntity<?> getOne(@RequestParam(value = "id", required = true) String id) {
        try{
            ArticleDTO response = articleService.getOne(id);
            return ResponseEntity.ok(response);
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
        }
    }

    @GetMapping("/get-articles")
    public ResponseEntity<?> getArticles(@AuthenticationPrincipal User currentUser) {
        Long userId = currentUser.getId();
        List<ArticleDTO> articles = articleService.getArticlesByAuthor(userId);
        return ResponseEntity.ok(articles);
    }
}
