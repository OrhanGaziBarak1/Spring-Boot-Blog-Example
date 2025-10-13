package com.blog.blog_project.exception;

import java.util.UUID;

public class FollowshipAlreadyExistException extends RuntimeException {
    public FollowshipAlreadyExistException(UUID userPublicId,
                                           UUID followedUserPublicId) {
        super("This followship is already exist between these users: %s %s".formatted(userPublicId, followedUserPublicId));
    }
}
