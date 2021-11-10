package com.antra.videomanager.domain.entity.base;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.Transient;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.sql.Timestamp;

@MappedSuperclass
public abstract class CommonEntity {

    @Column(name = "CreatedBy")
    private String createdBy;

    @Column(name = "CreatedDateTime")
    @CreationTimestamp
    private Timestamp createdDateTime;

    @Column(name = "LastUpdatedBy")
    private String lastUpdatedBy;

    @Column(name = "LastUpdatedDateTime")
    @UpdateTimestamp
    private Timestamp lastUpdatedDateTime;

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Timestamp getCreatedDateTime() {
        return createdDateTime;
    }

    public void setCreatedDateTime(Timestamp createdDateTime) {
        this.createdDateTime = createdDateTime;
    }

    public String getLastUpdatedBy() {
        return lastUpdatedBy;
    }

    public void setLastUpdatedBy(String lastUpdatedBy) {
        this.lastUpdatedBy = lastUpdatedBy;
    }

    public Timestamp getLastUpdatedDateTime() {
        return lastUpdatedDateTime;
    }

    public void setLastUpdatedDateTime(Timestamp lastUpdatedDateTime) {
        this.lastUpdatedDateTime = lastUpdatedDateTime;
    }
}
