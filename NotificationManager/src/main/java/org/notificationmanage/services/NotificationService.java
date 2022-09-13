package org.notificationmanage.services;

import java.util.List;

import org.notificationmanage.entities.Notification;
import org.notificationmanage.repositories.NotificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class NotificationService {

	@Autowired
	private NotificationRepository repo;
	
	public List<Notification> listAll() {
		return repo.findAll();
	}
	
	public void save(Notification notification) {
		repo.save(notification);
	}
	
	public Notification get(long id) {
		return repo.findById(id).get();
	}
	
	public void delete(long id) {
		repo.deleteById(id);
	}
}
