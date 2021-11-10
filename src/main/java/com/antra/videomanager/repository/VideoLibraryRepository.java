package com.antra.videomanager.repository;

import com.antra.videomanager.domain.entity.UserVideoLibrary;
import com.antra.videomanager.domain.entity.Video;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VideoLibraryRepository extends JpaRepository<UserVideoLibrary, String>, VideoLibraryRepositoryCustom{


    @Query("SELECT v FROM Video v " +
            "JOIN fetch v.userVideoLibrarySet uvl " +
            "JOIN fetch uvl.user u " +
            "JOIN uvl.libraryType_l l " +
            "WHERE u.userId = :id AND l.description = :type")
    List<Video> findVideosByLibraryType(@Param("id") String id,  @Param("type") String type);
}