package org.hatice.ikplus.repository.usermanagement;

import org.hatice.ikplus.entity.usermanagement.User;
import org.hatice.ikplus.view.userview.VwUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	@Query ("SELECT new org.hatice.ikplus.view.userview.VwUser(u.firstName,u.lastName,u.email) FROM User u")
	List<VwUser> getAllUsers();
	
	Optional<User> findByEmail(String email);
	
	Optional<User> findOptionalByEmailAndPassword(String email, String password);
}