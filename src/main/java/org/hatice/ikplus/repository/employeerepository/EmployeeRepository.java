package org.hatice.ikplus.repository.employeerepository;

import org.hatice.ikplus.entity.employeemanagement.Employee;
import org.hatice.ikplus.enums.EmployeeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
	
	
	List<Employee> findByCompanyId(Long companyId);
	
	List<Employee> findByStatus(EmployeeType status);
}