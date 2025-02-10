package org.hatice.ikplus.Repository.companymanagement;

import org.hatice.ikplus.entity.companymanagement.Company;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompanyRepository extends JpaRepository<Company, Long> {
}