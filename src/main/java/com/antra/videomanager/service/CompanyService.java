package com.antra.videomanager.service;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CompanyService {
    List<Object> getAllCompanies();
    Object getCompanyById(String companyId);
}
