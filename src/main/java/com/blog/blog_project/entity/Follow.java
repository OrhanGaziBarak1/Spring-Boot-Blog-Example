package com.blog.blog_project.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.util.Date;
import java.util.UUID;

@Entity
@Table(name = "follows",
    uniqueConstraints = {
        @UniqueConstraint(
                name = "unique_user_follow",
                columnNames = {"user_public_id", "followed_user_public_id"}
        )
    })
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class Follow {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, name = "user_public_id")
    private UUID userPublicId;

    @Column(nullable = false, name = "followed_user_public_id")
    private UUID followedUserPublicId;

    @CreationTimestamp
    @Column(name = "followed_at", updatable = false, nullable = false)
    private Date followedAt;

}
