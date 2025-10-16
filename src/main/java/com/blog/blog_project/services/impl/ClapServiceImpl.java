package com.blog.blog_project.services.impl;

import com.blog.blog_project.dto.ClapDTO;
import com.blog.blog_project.entity.Clap;
import com.blog.blog_project.exception.ClapNotFoundException;
import com.blog.blog_project.repository.ClapRepository;
import com.blog.blog_project.services.ClapService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ClapServiceImpl implements ClapService {

    private final ClapRepository clapRepository;
    private final Integer MAX_CLAP=50;

    @Transactional
    @Override
    public void clap(ClapDTO request, UUID userPublicId, String articleId) {
        if(clapRepository.existsByUserPublicIdAndArticleId(userPublicId, articleId)) {
            Clap clap = clapRepository.findByUserPublicIdAndArticleId(userPublicId, articleId)
                    .orElseThrow(()-> new ClapNotFoundException(userPublicId, articleId));
            clap.setClapCount(Math.min((clap.getClapCount()+request.getNewClap()), MAX_CLAP));
            clapRepository.save(clap);
        } else {
            Clap clap = new Clap();
            clap.setArticleId(articleId);
            clap.setUserPublicId(userPublicId);
            clap.setClapCount(Math.min(request.getNewClap(), MAX_CLAP));
            clapRepository.save(clap);
        }
    }

    @Transactional
    @Override
    public void deleteClap(UUID userPublicId, String articleId) {
        Clap clap = clapRepository.findByUserPublicIdAndArticleId(userPublicId, articleId)
                .orElseThrow(() -> new ClapNotFoundException(userPublicId, articleId));
        clapRepository.delete(clap);
    }
}
