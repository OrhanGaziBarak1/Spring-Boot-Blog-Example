package com.blog.blog_project.mapper;

import com.blog.blog_project.dto.ArticleDTO;
import com.blog.blog_project.entity.Article;
import org.mapstruct.Mapper;
import java.util.List;

@Mapper(componentModel = "spring")
public interface ArticleMapper {
    ArticleDTO toDTO (Article article);
    List<ArticleDTO> toDTOList (List<Article> articles);
}
