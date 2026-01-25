package com.blog.blog_project.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;
import java.util.UUID;

@Entity
@Table(name = "article")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class Article {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Setter(AccessLevel.PROTECTED)
    private String id;

    @Column(nullable = false, updatable = false, name = "user_public_id")
    private UUID userPublicId;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;

    @CreationTimestamp
    @Column(updatable = false, name = "created_at")
    private Date createdAt = new Date();

    @UpdateTimestamp
    @Column(name = "updated_at")
    private Date updatedAt;

    @Column(nullable = false, name = "deleted")
    private boolean isDeleted;

    @Column(name = "deleted_at")
    private Date deletedAt;

    @Override
    public String toString() {
        return "ID: %s\nAuthor: %d\nContent: %s".formatted(this.id, this.userPublicId, this.content);
    }
}
