package com.antra.videomanager.domain.entity;

import com.antra.videomanager.domain.entity.base.CommonEntity;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Set;

@Entity
@Table(name = "Company")
public class Company extends CommonEntity {

    @Id
    @GeneratedValue(generator = "company_UUID")
    @GenericGenerator(name = "company_UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "CompanyId")
    private String companyId;

    @Column(name = "CompanyName")
    private String companyName;

    @Column(name = "EIN")
    private String EIN;

    @Column(name = "Industry")
    private String industry;

    @Column(name = "DomainName")
    private String domainName;

    @Column(name = "InActiveDateTime")
    private Timestamp inActiveDateTime;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "company")
    private Set<People> peopleSet;

    public Company() {

    }

    public Company(String companyName, String EIN, String industry, String domainName, Timestamp inActiveDateTime, Set<People> peopleSet) {
        this.companyName = companyName;
        this.EIN = EIN;
        this.industry = industry;
        this.domainName = domainName;
        this.inActiveDateTime = inActiveDateTime;
        this.peopleSet = peopleSet;
    }

    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getEIN() {
        return EIN;
    }

    public void setEIN(String EIN) {
        this.EIN = EIN;
    }

    public String getIndustry() {
        return industry;
    }

    public void setIndustry(String industry) {
        this.industry = industry;
    }

    public String getDomainName() {
        return domainName;
    }

    public void setDomainName(String domainName) {
        this.domainName = domainName;
    }

    public Timestamp getInActiveDateTime() {
        return inActiveDateTime;
    }

    public void setInActiveDateTime(Timestamp inActiveDateTime) {
        this.inActiveDateTime = inActiveDateTime;
    }

    public Set<People> getPeopleSet() {
        return peopleSet;
    }

    public void setPeopleSet(Set<People> peopleSet) {
        this.peopleSet = peopleSet;
    }
}
