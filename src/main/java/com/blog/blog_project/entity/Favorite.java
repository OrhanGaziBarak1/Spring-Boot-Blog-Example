package com.blog.blog_project.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.util.Date;
import java.util.UUID;

@Entity
@Table(name = "favorites",
        uniqueConstraints = {
        @UniqueConstraint(
                name = "unique_article_favorite",
                columnNames = {"user_public_id", "article_id"}
        )
})
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class Favorite {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.PROTECTED)
    private Long id;

    @Column(nullable = false,
            updatable = false,
            name = "article_id")
    private String articleId;

    @Column(nullable = false,
            updatable = false,
            name = "user_public_id")
    private UUID userPublicId;

    @CreationTimestamp
    @Column(nullable = false,
            updatable = false,
            name = "favorite_at")
    private Date favoriteAt;
}
