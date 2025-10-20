package com.blog.blog_project.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.UUID;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ArticleDTO {
    private String content;
    private String id;
    private Date createdAt;
    private Date updatedAt;
    private long authorId;
    private UUID userPublicId;
    private Integer clapCount;
}
