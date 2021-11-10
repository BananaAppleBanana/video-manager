package com.antra.videomanager.domain.entity;

import com.antra.videomanager.domain.entity.base.CommonEntity;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "Tag_L", schema="dbo")
public class Tag_L extends CommonEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "tag_sequence")
    @SequenceGenerator(name = "tag_sequence", sequenceName = "TAG_SEQ")
    @Column(name = "TagId")
    private Integer tagId;

    @Column(name = "TagName")
    private String tagName;

    @Column(name = "Description")
    private String description;

    @OneToMany(mappedBy = "tag", fetch = FetchType.LAZY)
    private Set<VideoTag> videoTagSet;

    public Tag_L() {

    }

    public Tag_L(String tagName, String description) {
        this.tagName = tagName;
        this.description = description;
    }

    public Integer getTagId() {
        return tagId;
    }

    public void setTagId(Integer tagId) {
        this.tagId = tagId;
    }

    public String getTagName() {
        return tagName;
    }

    public void setTagName(String tagName) {
        this.tagName = tagName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<VideoTag> getVideoTagSet() {
        return videoTagSet;
    }

    public void setVideoTagSet(Set<VideoTag> videoTagSet) {
        this.videoTagSet = videoTagSet;
    }
}
