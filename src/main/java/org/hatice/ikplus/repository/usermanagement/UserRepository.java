package org.hatice.ikplus.repository.usermanagement;

import org.hatice.ikplus.entity.usermanagement.User;
import org.hatice.ikplus.enums.UserStatus;
import org.hatice.ikplus.view.userview.VwUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	@Query ("SELECT new org.hatice.ikplus.view.userview.VwUser(u.firstName,u.lastName,u.email) FROM User u")
	List<VwUser> getAllUsers();
	
	Optional<User> findByEmail(String email);
	
	Optional<User> findOptionalByEmailAndPassword(String email, String password);
	
	Optional<User> findByAuthId(UUID authId);
	

	Optional<User> findByResetToken(String resetToken);
	
	

	@Modifying
	@Transactional
	@Query("UPDATE User u SET u.status = :status WHERE u.authId = :authId")
	int updateUserStatus(UserStatus status, UUID authId);

}