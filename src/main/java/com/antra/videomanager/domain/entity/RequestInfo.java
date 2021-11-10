package com.antra.videomanager.domain.entity;

import com.antra.videomanager.domain.entity.base.CommonEntity;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "RequestInfo")
public class RequestInfo extends CommonEntity{

    @Id
    @GeneratedValue(generator = "request_UUID")
    @GenericGenerator(name = "request_UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "RequestId")
    private String requestId;

    @Column(name = "RequestedDateTime")
    private Timestamp requestedDateTime;

    @Column(name = "Message")
    private String message;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PersonId")
    private People person;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "VideoId")
    private Video video;

    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    public Timestamp getRequestedDateTime() {
        return requestedDateTime;
    }

    public void setRequestedDateTime(Timestamp requestedDateTime) {
        this.requestedDateTime = requestedDateTime;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public People getPerson() {
        return person;
    }

    public void setPerson(People person) {
        this.person = person;
    }

    public Video getVideo() {
        return video;
    }

    public void setVideo(Video video) {
        this.video = video;
    }
}
