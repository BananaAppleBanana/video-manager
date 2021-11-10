package com.antra.videomanager.domain.entity;

import com.antra.videomanager.domain.entity.base.CommonEntity;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "LibraryType_L")
public class LibraryType_L extends CommonEntity{

    @Id
    @GeneratedValue(generator = "libraryTypeCode_seq", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "libraryTypeCode_seq", sequenceName = "LIBRARYTYPE_L_SEQ")
    @Column(name = "LibraryTypeCode")
    private int libraryTypeCode;

    @Column(name = "Description")
    private String description;

    @Column(name = "LibraryType")
    private String libraryType;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "libraryType_l")
    private Set<UserVideoLibrary> userVideoLibrarySet;

    //TODO

}
