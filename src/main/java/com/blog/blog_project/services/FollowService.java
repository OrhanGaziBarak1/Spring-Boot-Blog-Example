package com.blog.blog_project.services;

import java.util.UUID;

public interface FollowService {
    void follow(UUID userPublicId, UUID followedUserPublicId);
    void unfollow(UUID userPublicId, UUID followedUserPublicId);
}
