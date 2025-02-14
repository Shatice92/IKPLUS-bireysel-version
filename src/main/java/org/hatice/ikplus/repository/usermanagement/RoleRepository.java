package org.hatice.ikplus.repository.usermanagement;

import org.hatice.ikplus.entity.usermanagement.Role;
import org.hatice.ikplus.enums.RoleName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
	
	List<Role> findByIdIn(List<Long> roleIds);
	
	Optional<Role> findByName(RoleName roleName);
}