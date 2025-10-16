package com.blog.blog_project.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.*;

import java.util.UUID;

@Entity
@Table(name = "claps",
    uniqueConstraints = {
        @UniqueConstraint(
                name = "who_clapped_article",
                columnNames = {"user_public_id", "article_id"}
        )
})
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class Clap {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.PROTECTED)
    private Long id;

    @Column(nullable = false, updatable = false, name = "article_id")
    private String articleId;

    @Column(nullable = false, updatable = false, name = "user_public_id")
    private UUID userPublicId;

    @Column(nullable = false, name = "clap_count")
    @Min(0)
    @Max(50)
    private Integer clapCount=0;

    @PrePersist
    @PreUpdate
    private void validateClapCount() {
        if (clapCount < 0 || clapCount > 50) {
            throw new IllegalStateException("Clap count must be between 0 and 50");
        }
    }
}
