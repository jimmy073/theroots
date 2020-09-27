package com.service.people;

import java.util.List;

import com.domain.people.User;
import org.springframework.stereotype.Service;

@Service
public interface UserService {

	User saveUser(User user);
	
	User findUser(String username);
	
	List<User> findAllUser();
	
}
