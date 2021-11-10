package com.antra.videomanager.domain.mapper;

import com.antra.videomanager.domain.dto.CompanyDTO;
import com.antra.videomanager.tool.mapper.Mapper;
import com.antra.videomanager.tool.mapper.Mapping;

@Mapper
public interface CompanyMapper {

    @Mapping(target = CompanyDTO.class)
    Object convertCompanyToCompanyDTO(Object company);
}
