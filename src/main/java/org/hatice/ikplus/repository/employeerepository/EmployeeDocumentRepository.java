package org.hatice.ikplus.repository.employeerepository;

import org.hatice.ikplus.entity.employeemanagement.EmployeeDocument;
import org.hatice.ikplus.mapper.EmployeeDocumentMapper;
import org.mapstruct.factory.Mappers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeDocumentRepository extends JpaRepository<EmployeeDocument, Long> {
}