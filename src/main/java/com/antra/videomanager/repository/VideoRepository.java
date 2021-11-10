package com.antra.videomanager.repository;


import com.antra.videomanager.domain.entity.Video;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VideoRepository extends JpaRepository<Video, String>, VideoTagRepositoryCustom {
}
