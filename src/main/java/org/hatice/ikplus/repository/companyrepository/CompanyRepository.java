package org.hatice.ikplus.repository.companyrepository;

import org.hatice.ikplus.entity.companymanagement.Company;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CompanyRepository extends JpaRepository<Company, Long> {
	
	List<Company> findByIsApproved(boolean isApproved);
}