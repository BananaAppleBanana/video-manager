package com.antra.videomanager.domain.dto;

import com.antra.videomanager.domain.response.TagResponse;

import java.sql.Timestamp;
import java.util.List;

public class VideoDTO {

    private String videoId;

    private String youtubeVideoId;

    private String title;

    private String description;

    private Timestamp publishedDateTime;

    private Boolean archivedFlag;

    private Boolean privateFlag;

    private String thumbNailJSON;

    private Integer youtubeCategoryId;

    private List<TagResponse> tag_ls;

    public VideoDTO() {
    }

    public VideoDTO(String videoId, String youtubeVideoId, String title, String description, Timestamp publishedDateTime, Boolean archivedFlag, Boolean privateFlag, String thumbNailJSON, Integer youtubeCategoryId) {
        this.youtubeVideoId = youtubeVideoId;
        this.title = title;
        this.description = description;
        this.publishedDateTime = publishedDateTime;
        this.archivedFlag = archivedFlag;
        this.privateFlag = privateFlag;
        this.thumbNailJSON = thumbNailJSON;
        this.youtubeCategoryId = youtubeCategoryId;
        this.videoId = videoId;
    }

    public String getVideoId() {
        return videoId;
    }

    public VideoDTO setVideoId(String videoId) {
        this.videoId = videoId;
        return this;
    }

    public String getYoutubeVideoId() {
        return youtubeVideoId;
    }

    public VideoDTO setYoutubeVideoId(String youtubeVideoId) {
        this.youtubeVideoId = youtubeVideoId;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public VideoDTO setTitle(String title) {
        this.title = title;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public VideoDTO setDescription(String description) {
        this.description = description;
        return this;
    }

    public Timestamp getPublishedDateTime() {
        return publishedDateTime;
    }

    public VideoDTO setPublishedDateTime(Timestamp publishedDateTime) {
        this.publishedDateTime = publishedDateTime;
        return this;
    }

    public Boolean getArchivedFlag() {
        return archivedFlag;
    }

    public VideoDTO setArchivedFlag(Boolean archivedFlag) {
        this.archivedFlag = archivedFlag;
        return this;
    }

    public Boolean getPrivateFlag() {
        return privateFlag;
    }

    public VideoDTO setPrivateFlag(Boolean privateFlag) {
        this.privateFlag = privateFlag;
        return this;
    }

    public String getThumbNailJSON() {
        return thumbNailJSON;
    }

    public VideoDTO setThumbNailJSON(String thumbNailJSON) {
        this.thumbNailJSON = thumbNailJSON;
        return this;
    }

    public Integer getYoutubeCategoryId() {
        return youtubeCategoryId;
    }

    public VideoDTO setYoutubeCategoryId(Integer youtubeCategoryId) {
        this.youtubeCategoryId = youtubeCategoryId;
        return this;
    }

    public List<TagResponse> getTag_ls() {
        return tag_ls;
    }

    public VideoDTO setTag_ls(List<TagResponse> tag_ls) {
        this.tag_ls = tag_ls;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        VideoDTO that = (VideoDTO) o;
        return getVideoId().equals(that.getVideoId());
    }

    @Override
    public int hashCode() {
        return getVideoId().hashCode();
    }

    @Override
    public String toString() {
        return "VideoDTO{" +
                "videoId='" + videoId + '\'' +
                ", youtubeVideoId='" + youtubeVideoId + '\'' +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", publishedDateTime=" + publishedDateTime +
                ", archivedFlag=" + archivedFlag +
                ", privateFlag=" + privateFlag +
                ", thumbNailJSON='" + thumbNailJSON + '\'' +
                ", youtubeCategoryId=" + youtubeCategoryId +
                ", tag_ls=" + tag_ls +
                '}';
    }
}
