package com.blog.blog_project.services;

import java.util.List;
import java.util.UUID;

public interface FollowService {
    void follow(UUID userPublicId, UUID followedUserPublicId);
    void unfollow(UUID userPublicId, UUID followedUserPublicId);

    List<UUID> getFollowedUsers(UUID userPublicId);
}
