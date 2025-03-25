package org.hatice.ikplus.repository.shiftandbreakrepository;

import org.hatice.ikplus.entity.shiftandbreakmanagement.Shift;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ShiftRepository extends JpaRepository<Shift,Long> {
	List<Shift> findByEmployeeId(Long employeeId);
}