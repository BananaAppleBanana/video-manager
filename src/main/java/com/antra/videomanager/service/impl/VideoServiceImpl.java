package com.antra.videomanager.service.impl;

import com.antra.videomanager.domain.mapper.VideoMapper;
import com.antra.videomanager.domain.request.VideoRequest;
import com.antra.videomanager.domain.entity.Tag_L;
import com.antra.videomanager.domain.entity.Video;
import com.antra.videomanager.domain.response.VideoResponse;
import com.antra.videomanager.repository.*;
import com.antra.videomanager.repository.VideoRepository;
import com.antra.videomanager.security.pojo.JwtUser;
import com.antra.videomanager.service.VideoService;
import com.antra.videomanager.repository.TagRepository;
import com.antra.videomanager.repository.VideoLibraryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.util.*;


@Service
public class VideoServiceImpl implements VideoService {

    private final VideoRepository videoRepository;
    private final VideoLibraryRepository videoLibraryRepository;
    private final TagRepository tagRepository;
    private final VideoMapper videoMapper;

    @Autowired
    public VideoServiceImpl(VideoRepository videoRepository, VideoLibraryRepository videoLibraryRepository, TagRepository tagRepository, VideoMapper videoMapper) {
        this.videoRepository = videoRepository;
        this.videoLibraryRepository = videoLibraryRepository;
        this.tagRepository = tagRepository;
        this.videoMapper = videoMapper;
    }

    @Override
    public List<Video> getAllVideos() {
        return videoRepository.findAll();
    }

    @Override
    public VideoResponse getVideos(String tagValue) {
        List<Video> videos = videoRepository.findVideos(tagValue);
        List<Object> res = new ArrayList<>();
        for(Video v: videos) {
            res.add(videoMapper.convertVideoToVideoDTO(v));
        }
        return new VideoResponse().setResults(res).setVirtualCount(res.size());
    }

    @Override
    public List<Tag_L> getAllTags() {
        return tagRepository.findAll();
    }

    @Override
    @Transactional
    public Video saveVideo(VideoRequest videoRequest) throws ParseException, NullPointerException {
        Video video = (Video) videoMapper.convertVideoRequestToVideo(videoRequest);
        return videoRepository.save(video);
    }

    @Override
    public Map<String, Long> getVideoLibraryCountForUser() {
        String userId = ((JwtUser)SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUserId();
        List<Object[]> res = videoLibraryRepository.countLibraryTypeForUser(userId);
        Map<String, Long> map = new HashMap<>();
        map.put("Recently Watched", 0l);
        map.put("Watch Later", 0l);
        map.put("Favorites", 0l);
        for(Object[] o: res) {
            map.put((String)o[0], (Long)o[1]);
        }
        return map;
    }

    @Override
    public List<Object> getVideoByLibraryType(String type) {
        String userId = ((JwtUser)SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUserId();
        List<Video> videos = videoLibraryRepository.findVideosByLibraryType(userId, type);
        List<Object> res = new ArrayList<>();
        for(Video v: videos) {
            res.add(videoMapper.convertVideoToVideoDTO(v));
        }
        return res;
    }
}
