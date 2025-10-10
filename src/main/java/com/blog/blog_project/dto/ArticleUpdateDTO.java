package com.blog.blog_project.dto;

import com.blog.blog_project.annotation.NoCode;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ArticleUpdateDTO {

    @NotBlank(message = "Content cannot be blank!")
    @NoCode
    private String content;
}
