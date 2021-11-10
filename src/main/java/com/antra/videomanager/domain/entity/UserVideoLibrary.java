package com.antra.videomanager.domain.entity;

import com.antra.videomanager.domain.entity.base.CommonEntity;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "UserVideoLibrary")
public class UserVideoLibrary extends CommonEntity{
    @Id
    @GeneratedValue(generator = "UserLibraryId_UUID")
    @GenericGenerator(name = "UserLibraryId_UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "UserLibraryId")
    private String userLibraryId;

    @Column(name = "ActionTakenTime")
    private Timestamp actionTakenTime;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "LibraryTypeCode")
    private LibraryType_L libraryType_l;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "UserId")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "VideoId")
    private Video video;

    public String getUserLibraryId() {
        return userLibraryId;
    }

    public void setUserLibraryId(String userLibraryId) {
        this.userLibraryId = userLibraryId;
    }

    public Timestamp getActionTakenTime() {
        return actionTakenTime;
    }

    public void setActionTakenTime(Timestamp actionTakenTime) {
        this.actionTakenTime = actionTakenTime;
    }

    public LibraryType_L getLibraryType_l() {
        return libraryType_l;
    }

    public void setLibraryType_l(LibraryType_L libraryType_l) {
        this.libraryType_l = libraryType_l;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Video getVideo() {
        return video;
    }

    public void setVideo(Video video) {
        this.video = video;
    }
}
