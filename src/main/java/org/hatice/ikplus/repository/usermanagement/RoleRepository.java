package org.hatice.ikplus.repository.usermanagement;

import org.hatice.ikplus.entity.usermanagement.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
}
