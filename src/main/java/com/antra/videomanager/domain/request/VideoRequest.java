package com.antra.videomanager.domain.request;

public class VideoRequest {

    private String youtubeVideoId;

    private String title;

    private String description;

    private String publishedDateTime;

    private Boolean archivedFlag;

    private Boolean privateFlag;

    private String processedAtDateTime;

    private String thumbNailJSON;

    private Integer youtubeCategoryId;

    public VideoRequest() {}

    public VideoRequest(String youtubeVideoId, String title, String description, String publishedDateTime, Boolean archivedFlag, Boolean privateFlag, String thumbNailJSON, Integer youtubeCategoryId, String processedAtDateTime) {
        this.youtubeVideoId = youtubeVideoId;
        this.title = title;
        this.description = description;
        this.publishedDateTime = publishedDateTime;
        this.archivedFlag = archivedFlag;
        this.privateFlag = privateFlag;
        this.thumbNailJSON = thumbNailJSON;
        this.youtubeCategoryId = youtubeCategoryId;
        this.processedAtDateTime = processedAtDateTime;
    }

    public String getYoutubeVideoId() {
        return youtubeVideoId;
    }

    public void setYoutubeVideoId(String youtubeVideoId) {
        this.youtubeVideoId = youtubeVideoId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPublishedDateTime() {
        return publishedDateTime;
    }

    public void setPublishedDateTime(String publishedDateTime) {
        this.publishedDateTime = publishedDateTime;
    }

    public Boolean getArchivedFlag() {
        return archivedFlag;
    }

    public void setArchivedFlag(Boolean archivedFlag) {
        this.archivedFlag = archivedFlag;
    }

    public Boolean getPrivateFlag() {
        return privateFlag;
    }

    public void setPrivateFlag(Boolean privateFlag) {
        this.privateFlag = privateFlag;
    }

    public String getProcessedAtDateTime() {
        return processedAtDateTime;
    }

    public void setProcessedAtDateTime(String processedAtDateTime) {
        this.processedAtDateTime = processedAtDateTime;
    }

    public String getThumbNailJSON() {
        return thumbNailJSON;
    }

    public void setThumbNailJSON(String thumbNailJSON) {
        this.thumbNailJSON = thumbNailJSON;
    }

    public Integer getYoutubeCategoryId() {
        return youtubeCategoryId;
    }

    public void setYoutubeCategoryId(Integer youtubeCategoryId) {
        this.youtubeCategoryId = youtubeCategoryId;
    }
}
