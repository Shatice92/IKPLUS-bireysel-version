package org.hatice.ikplus.repository.leaveandassetrepository;

import lombok.RequiredArgsConstructor;
import org.hatice.ikplus.entity.leaveandassetmanagement.Leaves;
import org.hatice.ikplus.service.leavesandassetsservice.LeavesService;
import org.springframework.data.jpa.repository.JpaRepository;



public interface LeavesRepository extends JpaRepository<Leaves,Long> {

	
	
}