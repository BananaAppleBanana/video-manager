package com.antra.videomanager.domain.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Address")
public class Address {

    @Id
    @Column(name = "AddressId")
    private String addressId;

    //TODO
}
