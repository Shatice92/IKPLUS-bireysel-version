package org.hatice.ikplus.repository.employeerepository;

import org.hatice.ikplus.dto.response.employeeresponse.EmployeeResponse;
import org.hatice.ikplus.entity.employeemanagement.Employee;
import org.hatice.ikplus.enums.EmployeeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
	
	
	List<Employee> findByCompanyId(Long companyId);
	
	Optional<Employee> findByIdAndCompanyId(Long id, Long companyId);
	
	List<Employee> findByStatus(EmployeeType status);
	
	@Query("SELECT new org.hatice.ikplus.dto.response.employeeresponse.EmployeeResponse(e.id, e.position,e.status, e.hireDate, u.firstName, u.lastName) " +
			"FROM Employee e JOIN User u ON e.id = u.employeeId " +
			"WHERE e.companyId = :companyId")
	List<EmployeeResponse> findEmployeesByCompanyId(@Param("companyId") Long companyId);
}