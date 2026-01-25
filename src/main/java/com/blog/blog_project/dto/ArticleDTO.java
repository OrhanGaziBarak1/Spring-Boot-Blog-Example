package com.blog.blog_project.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ArticleDTO {
    private String content;
    private String id;
    private Date createdAt;
    private Date updatedAt;
    private UUID userPublicId;
    private Integer clapCount;
}
