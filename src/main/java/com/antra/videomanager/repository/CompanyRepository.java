package com.antra.videomanager.repository;

import com.antra.videomanager.domain.entity.Company;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompanyRepository extends JpaRepository<Company, String>{
}
