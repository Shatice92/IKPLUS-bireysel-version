package org.hatice.ikplus.repository.usermanagement;

import org.hatice.ikplus.entity.usermanagement.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRoleRepository extends JpaRepository<UserRole, Long> {
}
