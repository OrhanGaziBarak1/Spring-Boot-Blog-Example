package com.blog.blog_project.controller;

import com.blog.blog_project.services.AuthenticationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.blog.blog_project.dto.AuthenticationDTO;
import com.blog.blog_project.dto.LoginDTO;
import com.blog.blog_project.dto.RegisterDTO;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/users") //Tüm endpointlerin başına tirnak icindeki ifadeyi ekler.
@RequiredArgsConstructor
public class AuthenticationController {
    private final AuthenticationService authService;

    @PostMapping("/register")
    public ResponseEntity<AuthenticationDTO> register(@Valid @RequestBody RegisterDTO request) {
        AuthenticationDTO response = authService.register(request);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/login")
    public ResponseEntity<AuthenticationDTO> login(@RequestBody LoginDTO request) {
        AuthenticationDTO response = authService.login(request);
        return ResponseEntity.ok(response);
    }
}