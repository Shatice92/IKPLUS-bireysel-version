package org.hatice.ikplus.repository.leaveandassetrepository;

import org.hatice.ikplus.entity.leaveandassetmanagement.LeaveTypes;
import org.hatice.ikplus.enums.leaveandassetenums.TypeLeaves;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LeaveTypesRepository extends JpaRepository<LeaveTypes, Long> {
	Optional<LeaveTypes> findByLeavesName(TypeLeaves leavesName);
}