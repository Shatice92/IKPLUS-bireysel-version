package org.hatice.ikplus.repository.shiftandbreakrepository;

import org.hatice.ikplus.entity.shiftandbreakmanagement.Break;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BreakRepository extends JpaRepository<Break,Long> {


}