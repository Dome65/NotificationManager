package org.notificationmanage.repositories;

import java.util.List;

import org.notificationmanage.email.EmailRequest;
import org.notificationmanage.entities.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends CrudRepository<User, Long> {

//	@Query("SELECT u FROM User u WHERE u.username = :username")
//	public User getUserByUsername(@Param("username") String username);

	User findByEmail(String email);
}