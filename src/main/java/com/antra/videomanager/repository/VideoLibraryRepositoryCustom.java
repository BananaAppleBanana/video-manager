package com.antra.videomanager.repository;

import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VideoLibraryRepositoryCustom {
    List<Object[]> countLibraryTypeForUser(String userId);
}
