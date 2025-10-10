package com.blog.blog_project.services;

import com.blog.blog_project.dto.AuthenticationDTO;
import com.blog.blog_project.dto.LoginDTO;
import com.blog.blog_project.dto.RegisterDTO;
import jakarta.transaction.Transactional;

public interface AuthenticationService {
    @Transactional
    AuthenticationDTO register(RegisterDTO request);

    AuthenticationDTO login(LoginDTO request);

    void checkAuthor(Long id);
}
