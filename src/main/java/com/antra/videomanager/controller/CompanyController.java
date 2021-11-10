package com.antra.videomanager.controller;

import com.antra.videomanager.domain.request.UserLoginRequest;
import com.antra.videomanager.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@RestController
public class CompanyController {

    private final CompanyService companyService;

    @Autowired
    public CompanyController(CompanyService companyService) {
        this.companyService = companyService;
    }


    //    @GetMapping("/company")
//    public Company getC() {
//        Company company = new Company(
//                "Antraaa",
//                "112331",
//                "industry",
//                "domain name",
//                null,
//                null
//        );
//        company.setLastUpdatedBy("47A37DC6-A76D-46BF-8751-1476507ED5A3");
//        company.setCreatedBy("47A37DC6-A76D-46BF-8751-1476507ED5A3");
//        return companyRepository.save(company);
//    }

    @GetMapping("/api/company")
    @PreAuthorize("hasAuthority('Admin')")
    public ResponseEntity<?> getAllCompanies() {
        List<Object> companies = companyService.getAllCompanies();
        return new ResponseEntity<Object>(companies, HttpStatus.OK);
    }


    @GetMapping("/api/company/{companyId}")
    @PreAuthorize("hasAuthority('Admin')")
    public ResponseEntity<?> getAllCompanyById(@PathVariable String companyId) {
        Object company = companyService.getCompanyById(companyId);
        return new ResponseEntity<Object>(company, HttpStatus.OK);
    }

}



