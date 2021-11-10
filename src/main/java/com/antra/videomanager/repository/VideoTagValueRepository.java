package com.antra.videomanager.repository;

import com.antra.videomanager.domain.entity.VideoTagValue;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VideoTagValueRepository extends JpaRepository<VideoTagValue, String>{
}
