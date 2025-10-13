package com.blog.blog_project.exception;

import java.util.UUID;

public class FollowshipNotFoundException extends RuntimeException{
    public FollowshipNotFoundException (UUID userPublicId,
                                        UUID followedUserPublicId) {
        super("Followship not found between these users: %s %s".formatted(userPublicId, followedUserPublicId));
    }
}
