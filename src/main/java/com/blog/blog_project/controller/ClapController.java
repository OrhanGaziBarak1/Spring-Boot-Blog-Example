package com.blog.blog_project.controller;

import com.blog.blog_project.dto.ClapDTO;
import com.blog.blog_project.entity.User;
import com.blog.blog_project.services.ClapService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/clap")
@RequiredArgsConstructor
public class ClapController {

    private final ClapService clapService;

    @PostMapping("/{articleId}")
    public ResponseEntity<Void> clap(
            @PathVariable String articleId,
            @Valid @RequestBody ClapDTO request,
            @AuthenticationPrincipal User currentUser
    ) {
        clapService.clap(request, currentUser.getPublicId(), articleId);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{articleId}")
    public ResponseEntity<Void> deleteClap(
            @PathVariable String articleId,
            @AuthenticationPrincipal User currentUser
    ) {
        clapService.deleteClap(currentUser.getPublicId(), articleId);
        return ResponseEntity.ok().build();
    }
}
