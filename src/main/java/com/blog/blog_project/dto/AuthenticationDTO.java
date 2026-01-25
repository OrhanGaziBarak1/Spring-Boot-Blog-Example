package com.blog.blog_project.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuthenticationDTO {
    private String fullName;
    private String email;
    private String token;
    private UUID publicId;
}