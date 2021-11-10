package com.antra.videomanager.domain.entity;

import com.antra.videomanager.domain.entity.base.CommonEntity;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "VideoTagValue")
public class VideoTagValue extends CommonEntity{

    @Id
    @GeneratedValue(generator = "video_tag_value_UUID")
    @GenericGenerator(name = "video_tag_value_UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "VideoTagValueId")
    private String VideoTagValueId;

    @Column(name = "TagValue")
    private String tagValue;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "VideoTagId", nullable = false)
    private VideoTag videoTag;

    public VideoTagValue() {
    }

    public VideoTagValue(String tagValue, VideoTag videoTag) {
        this.tagValue = tagValue;
        this.videoTag = videoTag;
    }

    public String getVideoTagValueId() {
        return VideoTagValueId;
    }

    public void setVideoTagValueId(String videoTagValueId) {
        VideoTagValueId = videoTagValueId;
    }

    public String getTagValue() {
        return tagValue;
    }

    public void setTagValue(String tagValue) {
        this.tagValue = tagValue;
    }

    public VideoTag getVideoTag() {
        return videoTag;
    }

    public void setVideoTag(VideoTag videoTag) {
        this.videoTag = videoTag;
    }
}
