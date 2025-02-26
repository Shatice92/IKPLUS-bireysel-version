package org.hatice.ikplus.repository.usermanagement;

import org.hatice.ikplus.entity.usermanagement.Authorization;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface AuthorizationRepository extends JpaRepository<Authorization, Long> {
	Optional<Authorization> findByAuthId(UUID authId);
}