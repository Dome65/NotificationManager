package org.notificationmanage.services;

import java.util.List;

import javax.transaction.Transactional;

import org.notificationmanage.entities.User;
import org.notificationmanage.entities.UserDto;
import org.springframework.stereotype.Service;

@Service
@Transactional
public interface UserService {

	void saveUser(UserDto userDto);

	User findUserByEmail(String email);

	List<UserDto> findAllUsers();
}