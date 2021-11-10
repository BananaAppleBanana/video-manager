package com.antra.videomanager.domain.dto;

public class PersonDTO {

    private String personId;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private String userId;
    private String companyId;
    private String companyName;
    private Boolean emailValidation;

    public String getPersonId() {
        return personId;
    }

    public PersonDTO setPersonId(String personId) {
        this.personId = personId;
        return this;
    }

    public String getFirstName() {
        return firstName;
    }

    public PersonDTO setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public PersonDTO setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public PersonDTO setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getPhone() {
        return phone;
    }

    public PersonDTO setPhone(String phone) {
        this.phone = phone;
        return this;
    }

    public String getUserId() {
        return userId;
    }

    public PersonDTO setUserId(String userId) {
        this.userId = userId;
        return this;
    }

    public String getCompanyId() {
        return companyId;
    }

    public PersonDTO setCompanyId(String companyId) {
        this.companyId = companyId;
        return this;
    }

    public String getCompanyName() {
        return companyName;
    }

    public PersonDTO setCompanyName(String companyName) {
        this.companyName = companyName;
        return this;
    }

    public Boolean getEmailValidation() {
        return emailValidation;
    }

    public PersonDTO setEmailValidation(Boolean emailValidation) {
        this.emailValidation = emailValidation;
        return this;
    }

    @Override
    public String toString() {
        return "PersonDTO{" +
                "personId='" + personId + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", userId='" + userId + '\'' +
                ", companyId='" + companyId + '\'' +
                ", companyName='" + companyName + '\'' +
                ", emailValidation=" + emailValidation +
                '}';
    }
}
