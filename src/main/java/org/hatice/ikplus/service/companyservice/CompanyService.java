package org.hatice.ikplus.service.companyservice;

import lombok.RequiredArgsConstructor;
import org.hatice.ikplus.dto.request.companyrequest.CompanyRequestDto;
import org.hatice.ikplus.dto.response.companyresponse.CompanyResponse;
import org.hatice.ikplus.entity.companymanagement.Company;
import org.hatice.ikplus.enums.CompanyStatus;
import org.hatice.ikplus.mapper.CompanyMapper;
import org.hatice.ikplus.repository.companyrepository.CompanyRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CompanyService {
	
	private final CompanyRepository companyRepository;
	private final CompanyMapper companyMapper;
	
	public void createCompany(CompanyRequestDto companyRequestDTO) {
		Company company = companyMapper.toEntity(companyRequestDTO);
		companyRepository.save(company);
	}

	public CompanyResponse approveCompany(Long id) {
		Company company = companyRepository.findById(id)
		                                   .orElseThrow(() -> new RuntimeException("Company not found"));
		company.setApproved(true);
		companyRepository.save(company);
		return companyMapper.toDto(company);
	}
	
	public CompanyResponse rejectCompany(Long id) {
		Company company = companyRepository.findById(id)
		                                   .orElseThrow(() -> new RuntimeException("Company not found"));
		company.setApproved(false);
		companyRepository.save(company);
		return companyMapper.toDto(company);
	}
	
	public CompanyResponse updateCompany(Long id, CompanyRequestDto updatedCompanyDTO) {
		Company company = companyRepository.findById(id)
		                                   .orElseThrow(() -> new RuntimeException("Company not found"));
		
		company.setName(updatedCompanyDTO.name());
		company.setEmailDomain(updatedCompanyDTO.emailDomain());
		
		companyRepository.save(company);
		return companyMapper.toDto(company);
	}
	
	public void deleteCompany(Long id) {
		companyRepository.deleteById(id);
	}
	
	public CompanyResponse getCompanyById(Long id) {
		Company company = companyRepository.findById(id)
		                                   .orElseThrow(() -> new RuntimeException("Company not found"));
		return companyMapper.toDto(company);
	}
	
	public List<CompanyResponse> getAllCompanies() {
		return companyRepository.findAll().stream()
		                        .map(companyMapper::toDto)
		                        .collect(Collectors.toList());
	}
	
}