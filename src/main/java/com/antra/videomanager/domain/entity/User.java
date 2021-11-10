package com.antra.videomanager.domain.entity;

import com.antra.videomanager.domain.entity.base.CommonEntity;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Set;

@Entity
@Table(name = "\"User\"")
public class User extends CommonEntity{

    @Id
    @GeneratedValue(generator = "user_UUID")
    @GenericGenerator(name = "user_UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "UserId")
    private String userId;

    @Column(name = "Password")
    private String password;

    @Column(name = "UserName")
    private String username;

    @Column(name = "InActiveDateTime")
    private Timestamp inActiveDateTime;

    @Column(name = "PasswordCreatedDateTime")
    private Timestamp passwordCreatedDateTime;

    @Column(name = "LastLoginDateTime")
    private Timestamp lastLoginDateTime;

    @Column(name = "LoginFailedCount")
    private Integer loginFailedCount;

    @Column(name = "LastLoginFailedDateTime")
    private Timestamp lastLoginFailedDateTime;

    @Column(name = "PasswordExpirationDateTime")
    private Timestamp passwordExpirationDateTime;

    @Column(name = "LockoutDateTime")
    private Timestamp lockoutDateTime;

    @Column(name = "ProfileImagePath")
    private String profileImagePath;

    @Column(name = "CurrentLoginTime")
    private Timestamp currentLoginTime;

    @OneToOne(fetch = FetchType.LAZY, mappedBy = "user")
    private People person;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
    private Set<UserRole> userRoleSet;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
    private Set<UserVideoLibrary> userVideoLibrarySet;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Timestamp getInActiveDateTime() {
        return inActiveDateTime;
    }

    public void setInActiveDateTime(Timestamp inActiveDateTime) {
        this.inActiveDateTime = inActiveDateTime;
    }

    public Timestamp getPasswordCreatedDateTime() {
        return passwordCreatedDateTime;
    }

    public void setPasswordCreatedDateTime(Timestamp passwordCreatedDateTime) {
        this.passwordCreatedDateTime = passwordCreatedDateTime;
    }

    public Timestamp getLastLoginDateTime() {
        return lastLoginDateTime;
    }

    public void setLastLoginDateTime(Timestamp lastLoginDateTime) {
        this.lastLoginDateTime = lastLoginDateTime;
    }

    public Integer getLoginFailedCount() {
        return loginFailedCount;
    }

    public void setLoginFailedCount(Integer loginFailedCount) {
        this.loginFailedCount = loginFailedCount;
    }

    public Timestamp getLastLoginFailedDateTime() {
        return lastLoginFailedDateTime;
    }

    public void setLastLoginFailedDateTime(Timestamp lastLoginFailedDateTime) {
        this.lastLoginFailedDateTime = lastLoginFailedDateTime;
    }

    public Timestamp getPasswordExpirationDateTime() {
        return passwordExpirationDateTime;
    }

    public void setPasswordExpirationDateTime(Timestamp passwordExpirationDateTime) {
        this.passwordExpirationDateTime = passwordExpirationDateTime;
    }

    public Timestamp getLockoutDateTime() {
        return lockoutDateTime;
    }

    public void setLockoutDateTime(Timestamp lockoutDateTime) {
        this.lockoutDateTime = lockoutDateTime;
    }

    public String getProfileImagePath() {
        return profileImagePath;
    }

    public void setProfileImagePath(String profileImagePath) {
        this.profileImagePath = profileImagePath;
    }

    public Timestamp getCurrentLoginTime() {
        return currentLoginTime;
    }

    public void setCurrentLoginTime(Timestamp currentLoginTime) {
        this.currentLoginTime = currentLoginTime;
    }

    public People getPerson() {
        return person;
    }

    public void setPerson(People person) {
        this.person = person;
    }

    public Set<UserRole> getUserRoleSet() {
        return userRoleSet;
    }

    public void setUserRoleSet(Set<UserRole> userRoleSet) {
        this.userRoleSet = userRoleSet;
    }

    public Set<UserVideoLibrary> getUserVideoLibrarySet() {
        return userVideoLibrarySet;
    }

    public void setUserVideoLibrarySet(Set<UserVideoLibrary> userVideoLibrarySet) {
        this.userVideoLibrarySet = userVideoLibrarySet;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        return getUserId().equals(user.getUserId()) && getUsername().equals(user.getUsername());
    }

    @Override
    public int hashCode() {
        int result = getUserId().hashCode();
        result = 31 * result + getUsername().hashCode();
        return result;
    }

}
