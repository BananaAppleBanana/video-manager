package com.antra.videomanager.repository;

import com.antra.videomanager.domain.entity.Video;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VideoTagRepositoryCustom {
    List<Video> findVideos(String tagValue);
}
