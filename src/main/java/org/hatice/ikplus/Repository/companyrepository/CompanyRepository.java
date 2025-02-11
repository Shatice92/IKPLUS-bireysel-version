package org.hatice.ikplus.Repository.companyrepository;

import org.hatice.ikplus.entity.companymanagement.Company;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompanyRepository extends JpaRepository<Company, Long> {
}