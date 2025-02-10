package org.hatice.ikplus.repository.shiftandbreakmanagement;

import org.hatice.ikplus.entity.shiftandbreakmanagement.Break;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public class BreakRepository extends JpaRepository<Break,Long> {


}