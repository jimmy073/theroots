package com.service.people;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.domain.people.Role;
import com.domain.people.User;
import com.repository.people.UserRepository;

@Service
public class UserServiceImpl implements UserService, UserDetails {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private UserRepository userRepositry;
	private User user; 
	
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	@Autowired
	public void setUserRepositry(UserRepository userRepositry) {
		this.userRepositry = userRepositry;
	}

	@Override
	public User saveUser(User user) {
		user.setStatus(true);
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		return userRepositry.save(user);
	}

	@Override
	public User findUser(String username) {
		return userRepositry.findByUsername(username);
			}

	@Override
	public List<User> findAllUser() {
		return userRepositry.findAll();
	}

	
	
	public UserServiceImpl(User user) {
		super();
		this.user = user;
	}

	//METHODS FOR SECURITY PURPOSES
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		Collection<GrantedAuthority> authorities = new HashSet<GrantedAuthority>();
		Collection<Role> roles = (Collection<Role>) user.getRoles();
        for(Role role: roles){
            authorities.add(new SimpleGrantedAuthority(role.getName()));
        }
          
        return authorities;
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return user.getPassword();
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return user.getUsername();
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}

}
