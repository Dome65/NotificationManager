package org.notificationmanage.services;



import java.util.List;

import org.notificationmanage.entities.User;
import org.notificationmanage.entities.UserDto;

public interface UserService {
	
	void saveUser(UserDto userDto);

	User findUserByEmail(String email);

	List<UserDto> findAllUsers();
}