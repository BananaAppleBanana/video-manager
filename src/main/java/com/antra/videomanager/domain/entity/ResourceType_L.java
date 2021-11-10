package com.antra.videomanager.domain.entity;

import com.antra.videomanager.domain.entity.base.CommonEntity;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

public class ResourceType_L extends CommonEntity {
    @Id
    @GeneratedValue(generator = "person_UUID")
    @GenericGenerator(name = "person_UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "PersonId")
    private String personId;

    //TODO
}
