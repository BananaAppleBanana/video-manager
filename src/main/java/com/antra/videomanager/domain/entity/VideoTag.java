package com.antra.videomanager.domain.entity;

import com.antra.videomanager.domain.entity.base.CommonEntity;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "VideoTag")
public class VideoTag extends CommonEntity{

    @Id
    @GeneratedValue(generator = "video_tag_UUID")
    @GenericGenerator(name = "video_tag_UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "VideoTagId")
    private String videoTagId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="VideoId", nullable=false)
    private Video video;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="TagTypeId", nullable=false)
    private Tag_L tag;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "videoTag")
    private Set<VideoTagValue> videoTagValueSet;

    public String getVideoTagId() {
        return videoTagId;
    }

    public void setVideoTagId(String videoTagId) {
        this.videoTagId = videoTagId;
    }

    public Video getVideo() {
        return video;
    }

    public void setVideo(Video video) {
        this.video = video;
    }

    public Tag_L getTag() {
        return tag;
    }

    public void setTag(Tag_L tag) {
        this.tag = tag;
    }

    public Set<VideoTagValue> getVideoTagValueSet() {
        return videoTagValueSet;
    }

    public void setVideoTagValueSet(Set<VideoTagValue> videoTagValueSet) {
        this.videoTagValueSet = videoTagValueSet;
    }
}
