package org.notificationmanage.repositories;

import org.notificationmanage.email.EmailRequest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmailRequestRepository extends JpaRepository<EmailRequest, Long> {

}
