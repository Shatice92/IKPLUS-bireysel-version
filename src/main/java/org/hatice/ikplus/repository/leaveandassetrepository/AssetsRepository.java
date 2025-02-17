package org.hatice.ikplus.repository.leaveandassetrepository;

import org.hatice.ikplus.entity.leaveandassetmanagement.Assets;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AssetsRepository extends JpaRepository<Assets, Long> {
	List<Assets> findByEmployeeId(Long employeeId);
}