package com.antra.videomanager.controller;

import com.antra.videomanager.domain.request.VideoRequest;
import com.antra.videomanager.service.TagService;
import com.antra.videomanager.service.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

@RestController
public class VideoController {

    private final VideoService videoService;

    private final TagService tagService;

    @Autowired
    public VideoController(VideoService videoService, TagService tagService) {
        this.videoService = videoService;
        this.tagService = tagService;
    }

    @GetMapping("/api/video")
    @PreAuthorize("hasAuthority('Admin')")
    public ResponseEntity<?> getAllVideosAndTags(@RequestParam(required = false, value = "TagValue") String tagValue) {
        return new ResponseEntity<>(videoService.getVideos(tagValue), HttpStatus.OK);
    }

    @PostMapping(value = "/api/video", consumes = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> saveVideo(@RequestBody VideoRequest videoDTO) throws ParseException, NullPointerException{
        return new ResponseEntity<>(videoService.saveVideo(videoDTO), HttpStatus.CREATED);
    }

    //Not efficient, need to change
    @GetMapping("/api/video/tag/count")
    @PreAuthorize("hasAuthority('User')")
    public ResponseEntity<?> getAllTagsAndCount() {
        return new ResponseEntity<>(tagService.getTagAndCount(), HttpStatus.OK);
    }

    @PutMapping("/api/video/{id}/activate")
    @PreAuthorize("hasAuthority('Admin')")
    public ResponseEntity<?> archivePutVideo(@PathVariable String id) {
        //TODO
        return null;
    }

    @DeleteMapping("/api/video/{id}/archive")
    @PreAuthorize("hasAuthority('Admin')")
    public ResponseEntity<?> archiveDeleteVideo(@PathVariable String id) {
        //TODO
        return null;
    }

    @GetMapping("/api/videolibrary/count")
    @PreAuthorize("hasAuthority('User')")
    public ResponseEntity<?> getVideoLibraryCount() {
        Map<String, Long> vlCount = videoService.getVideoLibraryCountForUser();
        return new ResponseEntity<Object>(vlCount, HttpStatus.OK);
    }

    @GetMapping("/api/videolibrary/{librarytype}")
    @PreAuthorize("hasAuthority('User')")
    public ResponseEntity<?> getVideoLibraryForUser(@PathVariable String librarytype) {
        List<Object> videoDTOList = videoService.getVideoByLibraryType(librarytype);
        return new ResponseEntity<Object>(videoDTOList, HttpStatus.OK);
    }

    @PostMapping("/api/videolibrary/")
    @PreAuthorize("hasAuthority('User')")
    public ResponseEntity<?> addToLibrary() {
        //TODO
        return null;
    }

    @DeleteMapping("/api/videolibrary/")
    @PreAuthorize("hasAuthority('User')")
    public ResponseEntity<?> removeFromLibrary() {
        //TODO
        return null;
    }

}
