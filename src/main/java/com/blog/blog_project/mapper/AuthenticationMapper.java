package com.blog.blog_project.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.blog.blog_project.dto.AuthenticationDTO;
import com.blog.blog_project.entity.User;

@Mapper(componentModel = "spring")
public interface AuthenticationMapper {

    @Mapping(target = "token", source = "token")
    AuthenticationDTO toDto(User user, String token);
    
} 
