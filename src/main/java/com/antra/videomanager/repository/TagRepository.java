package com.antra.videomanager.repository;

import com.antra.videomanager.domain.entity.Tag_L;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TagRepository extends JpaRepository<Tag_L, Integer>{

}
