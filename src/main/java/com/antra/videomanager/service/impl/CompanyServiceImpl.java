package com.antra.videomanager.service.impl;

import com.antra.videomanager.domain.entity.Company;
import com.antra.videomanager.domain.mapper.CompanyMapper;
import com.antra.videomanager.repository.CompanyRepository;
import com.antra.videomanager.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CompanyServiceImpl implements CompanyService {

    private final CompanyRepository companyRepository;
    private final CompanyMapper companyMapper;

    @Autowired
    public CompanyServiceImpl(CompanyMapper companyMapper, CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
        this.companyMapper = companyMapper;
    }

    @Override
    public List<Object> getAllCompanies() {
        List<Company> companies = companyRepository.findAll();
        List<Object> res = new ArrayList<>();
        for(Company c: companies) {
            res.add(companyMapper.convertCompanyToCompanyDTO(c));
        }
        return res;
    }

    @Override
    public Object getCompanyById(String companyId) {
        Optional<Company> companyOptional = companyRepository.findById(companyId);
        return companyMapper.convertCompanyToCompanyDTO(
                companyOptional.orElseThrow(() -> new IllegalArgumentException("can't find company with id " + companyId))
        );
    }
}
