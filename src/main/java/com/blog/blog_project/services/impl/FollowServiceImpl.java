package com.blog.blog_project.services.impl;

import com.blog.blog_project.entity.Follow;
import com.blog.blog_project.exception.AuthorNotFoundException;
import com.blog.blog_project.exception.FollowshipAlreadyExistException;
import com.blog.blog_project.exception.FollowshipNotFoundException;
import com.blog.blog_project.repository.FollowRepository;
import com.blog.blog_project.repository.UserRepository;
import com.blog.blog_project.services.FollowService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class FollowServiceImpl implements FollowService {
    
    private final FollowRepository followRepository;
    private final UserRepository userRepository;

    @Transactional
    @Override
    public void follow(UUID userPublicId, UUID followedUserPublicId) {

        if(userPublicId.equals(followedUserPublicId)) throw new IllegalArgumentException(
                "You cannot follow yourself!"
        );

        if(!userRepository.existsByPublicId(followedUserPublicId)) throw new AuthorNotFoundException(followedUserPublicId);

        if(followRepository.existsByUserPublicIdAndFollowedUserPublicId(
                userPublicId, followedUserPublicId)) {
            throw new FollowshipAlreadyExistException(userPublicId, followedUserPublicId);
        }

        Follow followship = new Follow();

        followship.setUserPublicId(userPublicId);
        followship.setFollowedUserPublicId(followedUserPublicId);

        followRepository.save(followship);
        
    }

    @Transactional
    @Override
    public void unfollow(UUID userPublicId, UUID followedUserPublicId) {
        Follow followship = followRepository.findByUserPublicIdAndFollowedUserPublicId(
                userPublicId, followedUserPublicId
        ).orElseThrow(() -> new FollowshipNotFoundException(userPublicId, followedUserPublicId));

        followRepository.delete(followship);
    }

}
