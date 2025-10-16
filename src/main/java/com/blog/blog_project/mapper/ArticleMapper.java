package com.blog.blog_project.mapper;

import com.blog.blog_project.dto.ArticleDTO;
import com.blog.blog_project.entity.Article;
import com.blog.blog_project.repository.ClapRepository;
import org.mapstruct.AfterMapping;
import org.mapstruct.Context;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ArticleMapper {

    @Mapping(target = "clapCount", ignore = true)
    ArticleDTO toDTO(Article article, @Context ClapRepository clapRepository);
    @Mapping(target = "clapCount", ignore = true)
    List<ArticleDTO> toDTOList(List<Article> articles, @Context ClapRepository clapRepository);

    @AfterMapping
    default void afterMapping(@MappingTarget ArticleDTO dto, Article article, @Context ClapRepository clapRepository) {
        Long totalClaps = clapRepository.getTotalClapsForArticle(article.getId());
        dto.setClapCount(totalClaps != null ? totalClaps.intValue() : 0);
    }
}
