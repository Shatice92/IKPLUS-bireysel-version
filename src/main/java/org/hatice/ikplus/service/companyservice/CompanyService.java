package org.hatice.ikplus.service.companyservice;

import lombok.RequiredArgsConstructor;
import org.hatice.ikplus.dto.request.companyrequest.CompanyRequestDto;
import org.hatice.ikplus.dto.response.companyresponse.CompanyResponse;
import org.hatice.ikplus.entity.companymanagement.Company;
import org.hatice.ikplus.mapper.CompanyMapper;
import org.hatice.ikplus.repository.companyrepository.CompanyRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CompanyService {
	
	private final CompanyRepository companyRepository;
	private final CompanyMapper companyMapper;
	
	public void createCompany(CompanyRequestDto companyRequestDTO) {
		Company company = companyMapper.toEntity(companyRequestDTO);
		companyRepository.save(company);
	}
}