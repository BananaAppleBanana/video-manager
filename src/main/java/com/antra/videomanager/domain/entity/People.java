package com.antra.videomanager.domain.entity;

import com.antra.videomanager.domain.entity.base.CommonEntity;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "People")
public class People extends CommonEntity{

    @Id
    @GeneratedValue(generator = "person_UUID")
    @GenericGenerator(name = "person_UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "PersonId")
    private String personId;

    @Column(name = "FirstName")
    private String firstName;

    @Column(name = "LastName")
    private String lastName;

    @Column(name = "Email")
    private String email;

    @Column(name = "Phone")
    private String phone;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CompanyId")
    private Company company;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "person")
    private Set<RequestInfo> requestInfoSet;

    @Column(name = "userId", insertable = false, updatable = false)
    private String userId;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userId")
    private User user;

    public String getPersonId() {
        return personId;
    }

    public void setPersonId(String personId) {
        this.personId = personId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public Set<RequestInfo> getRequestInfoSet() {
        return requestInfoSet;
    }

    public void setRequestInfoSet(Set<RequestInfo> requestInfoSet) {
        this.requestInfoSet = requestInfoSet;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
