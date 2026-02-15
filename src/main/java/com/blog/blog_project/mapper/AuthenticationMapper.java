package com.blog.blog_project.mapper;

import org.mapstruct.Mapper;

import com.blog.blog_project.dto.AuthenticationDTO;
import com.blog.blog_project.entity.User;

@Mapper(componentModel = "spring")
public interface AuthenticationMapper {
    
    AuthenticationDTO toDto(User user, String token);
    
} 
