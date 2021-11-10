package com.antra.videomanager.domain.entity;

import com.antra.videomanager.domain.entity.base.CommonEntity;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Set;

@Entity
@Table(name = "Video")
public class Video extends CommonEntity{

    @Id
    @GeneratedValue(generator = "video_UUID")
    @GenericGenerator(name = "video_UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "VideoId")
    private String videoId;

    @Column(name = "YoutubeVideoId")
    private String youtubeVideoId;

    @Column(name = "Title")
    private String title;

    @Column(name = "Description")
    private String description;

    @Column(name = "PublishedDateTime")
    private Timestamp publishedDateTime;

    @Column(name = "ArchivedFlag")
    private Boolean archivedFlag;

    @Column(name = "PrivateFlag")
    private Boolean privateFlag;

    @Column(name = "ProcessedAtDateTime")
    private Timestamp processedAtDateTime;

    @Column(name = "ThumbNailJSON")
    private String thumbNailJSON;

    @Column(name = "YoutubeCategoryId")
    private Integer youtubeCategoryId;

    @OneToMany(mappedBy="video", fetch = FetchType.LAZY)
    private Set<VideoTag> videoTagSet;

    @OneToMany(mappedBy = "video", fetch = FetchType.LAZY)
    private Set<RequestInfo> requestInfoSet;

    @OneToMany(mappedBy = "video", fetch = FetchType.LAZY)
    private Set<UserVideoLibrary> userVideoLibrarySet;

    public Video() {}

    public Video(String youtubeVideoId, String title, String description, Timestamp publishedDateTime, Boolean archivedFlag, Boolean privateFlag, Timestamp processedAtDateTime, String thumbNailJSON, Integer youtubeCategoryId) {
        this.youtubeVideoId = youtubeVideoId;
        this.title = title;
        this.description = description;
        this.publishedDateTime = publishedDateTime;
        this.archivedFlag = archivedFlag;
        this.privateFlag = privateFlag;
        this.processedAtDateTime = processedAtDateTime;
        this.thumbNailJSON = thumbNailJSON;
        this.youtubeCategoryId = youtubeCategoryId;
    }

    public String getVideoId() {
        return videoId;
    }

    public void setVideoId(String videoId) {
        this.videoId = videoId;
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

    public Timestamp getPublishedDateTime() {
        return publishedDateTime;
    }

    public void setPublishedDateTime(Timestamp publishedDateTime) {
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

    public Timestamp getProcessedAtDateTime() {
        return processedAtDateTime;
    }

    public void setProcessedAtDateTime(Timestamp processedAtDateTime) {
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

    public Set<VideoTag> getVideoTagSet() {
        return videoTagSet;
    }

    public void setVideoTagSet(Set<VideoTag> videoTagSet) {
        this.videoTagSet = videoTagSet;
    }


}
