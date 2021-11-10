package com.antra.videomanager.domain.response;

import com.antra.videomanager.domain.Response;
import com.antra.videomanager.domain.entity.Video;

import java.util.List;

public class TagResponse implements Response {

    private String tagName;

    private String description;

    private List<Video> videos;

    public TagResponse() {
    }

    public TagResponse(String tagName, String description) {
        this.tagName = tagName;
        this.description = description;
    }

    public String getTagName() {
        return tagName;
    }

    public TagResponse setTagName(String tagName) {
        this.tagName = tagName;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public TagResponse setDescription(String description) {
        this.description = description;
        return this;
    }

    public List<Video> getVideos() {
        return videos;
    }

    public TagResponse setVideos(List<Video> videos) {
        this.videos = videos;
        return this;
    }
}
