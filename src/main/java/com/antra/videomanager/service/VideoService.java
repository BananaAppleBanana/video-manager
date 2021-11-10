package com.antra.videomanager.service;

import com.antra.videomanager.domain.request.VideoRequest;
import com.antra.videomanager.domain.entity.Tag_L;
import com.antra.videomanager.domain.entity.Video;
import com.antra.videomanager.domain.response.VideoResponse;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

@Service
public interface VideoService {
    VideoResponse getVideos(String tagValue);
    List<Video> getAllVideos();
    List<Tag_L> getAllTags();
    Video saveVideo(VideoRequest videoDTO) throws ParseException;
    Map<String, Long> getVideoLibraryCountForUser();
    List<Object> getVideoByLibraryType(String type);
}
