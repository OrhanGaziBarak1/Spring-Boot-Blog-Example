package com.blog.blog_project.dto;

import com.blog.blog_project.annotation.NoCode;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ArticleCreateDTO {
    @NotNull(message = "Author id cannot be blank!")
    @Positive(message = "Author id must be positive!")
    private long authorId;

    @NotBlank(message = "Content can not being blank!")
    @NoCode
    private String content;

}
