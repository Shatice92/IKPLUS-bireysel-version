package org.hatice.ikplus.repository.usermanagement;

import org.hatice.ikplus.entity.usermanagement.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRoleRepository extends JpaRepository<UserRole, Long> {
	List<UserRole> findByUserId(Long userId);
	List<UserRole> findByRoleId(Long roleId);
}