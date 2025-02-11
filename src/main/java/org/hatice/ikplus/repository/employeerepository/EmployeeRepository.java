package org.hatice.ikplus.repository.employeerepository;

import org.hatice.ikplus.entity.employeemanagement.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {


}