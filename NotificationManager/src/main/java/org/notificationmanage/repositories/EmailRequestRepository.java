package org.notificationmanage.repositories;

import java.util.List;

import org.notificationmanage.email.EmailRequest;
import org.notificationmanage.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmailRequestRepository extends JpaRepository<EmailRequest, Long> {

	List<EmailRequest> findByUser(User user);
}
