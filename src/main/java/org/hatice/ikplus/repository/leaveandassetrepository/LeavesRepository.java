package org.hatice.ikplus.repository.leaveandassetrepository;

import lombok.RequiredArgsConstructor;
import org.hatice.ikplus.entity.leaveandassetmanagement.Leaves;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface LeavesRepository extends JpaRepository<Leaves,Long> {
	
	List<Leaves> findByEmployeeId(Long employeeId);
	
	
	
}