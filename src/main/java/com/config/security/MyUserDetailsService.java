package com.config.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.domain.people.User;
import com.repository.people.UserRepository;
import com.service.people.UserServiceImpl;

@Service
public class MyUserDetailsService implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepository.findByUsername(username);
		
		if(user==null)
			throw new UsernameNotFoundException("User 404");
		return  new UserServiceImpl(user);
	}
	
	@Bean 
	public User user() {
		return new User();
	}

}
