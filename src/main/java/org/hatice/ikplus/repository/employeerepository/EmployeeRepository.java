package org.hatice.ikplus.repository.employeerepository;

import org.hatice.ikplus.entity.employeemanagement.Employee;
import org.hatice.ikplus.enums.EmployeeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
	
	
	List<Employee> findByCompanyId(Long companyId);
	
	Optional<Employee> findByIdAndCompanyId(Long id, Long companyId);
	
	List<Employee> findByStatus(EmployeeType status);
}