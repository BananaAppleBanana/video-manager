package com.antra.videomanager.domain.response;

import com.antra.videomanager.domain.Response;
import com.antra.videomanager.domain.dto.PersonDTO;

import java.sql.Timestamp;
import java.util.List;

public class UserInfoResponse implements Response {
    private String userId;
    private String username;
    private Timestamp inActiveDateTime;
    private PersonDTO person;
    private List<Integer> roles;

    public String getUserId() {
        return userId;
    }

    public UserInfoResponse setUserId(String userId) {
        this.userId = userId;
        return this;
    }

    public String getUsername() {
        return username;
    }

    public UserInfoResponse setUsername(String username) {
        this.username = username;
        return this;
    }

    public Timestamp getInActiveDateTime() {
        return inActiveDateTime;
    }

    public UserInfoResponse setInActiveDateTime(Timestamp inActiveDateTime) {
        this.inActiveDateTime = inActiveDateTime;
        return this;
    }

    public PersonDTO getPerson() {
        return person;
    }

    public UserInfoResponse setPerson(PersonDTO person) {
        this.person = person;
        return this;
    }

    public List<Integer> getRoles() {
        return roles;
    }

    public UserInfoResponse setRoles(List<Integer> roles) {
        this.roles = roles;
        return this;
    }

    @Override
    public String toString() {
        return "UserInfoResponse{" +
                "username='" + username + '\'' +
                ", inActiveDateTime=" + inActiveDateTime +
                ", person=" + person +
                ", roles=" + roles +
                '}';
    }
}
