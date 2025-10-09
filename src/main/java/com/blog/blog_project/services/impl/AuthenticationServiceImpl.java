package com.blog.blog_project.services.impl;

import com.blog.blog_project.dto.AuthenticationDTO;
import com.blog.blog_project.dto.LoginDTO;
import com.blog.blog_project.dto.RegisterDTO;
import com.blog.blog_project.entity.User;
import com.blog.blog_project.repository.UserRepository;
import com.blog.blog_project.services.AuthenticationService;
import com.blog.blog_project.services.JwtService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    @Transactional
    @Override
    public AuthenticationDTO register(RegisterDTO request) {
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new IllegalArgumentException("This email is using already");
        }

        User user = new User();
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setFullName(request.getFullName());

        User savedUser = userRepository.save(user);
        String token = jwtService.generateToken(savedUser);

        return new AuthenticationDTO(
                savedUser.getFullName(),
                savedUser.getEmail(),
                token
        );
    }

    @Override
    public AuthenticationDTO login(LoginDTO request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );

        User user = userRepository.findByEmail(request.getEmail()).orElseThrow(()-> new RuntimeException("User not found!"));

        String token = jwtService.generateToken(user);

        return new AuthenticationDTO(
                user.getFullName(),
                user.getEmail(),
                token
        );

    }
}
