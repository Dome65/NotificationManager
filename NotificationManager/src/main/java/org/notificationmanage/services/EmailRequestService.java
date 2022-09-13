package org.notificationmanage.services;

import java.util.List;

import org.notificationmanage.email.EmailRequest;
import org.notificationmanage.repositories.EmailRequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class EmailRequestService {
	
	@Autowired
	private EmailRequestRepository repo;

	public List<EmailRequest> listAll() {
		return repo.findAll();
	}

	public void save(EmailRequest emailRequest) {
		repo.save(emailRequest);
	}

	public EmailRequest get(long id) {
		return repo.findById(id).get();
	}

	public void delete(long id) {
		repo.deleteById(id);
	}
}
