package com.antra.videomanager.domain.dto;


public class CompanyDTO {

    private String companyId;
    private String companyName;
    private String EIN;
    private String industry;
    private String domainName;

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


    public static class Builder extends CompanyDTO{

    }

    @Override
    public String toString() {
        return "CompanyDTO{" +
                "companyId='" + companyId + '\'' +
                ", companyName='" + companyName + '\'' +
                ", EIN='" + EIN + '\'' +
                ", industry='" + industry + '\'' +
                ", domainName='" + domainName + '\'' +
                '}';
    }
}

