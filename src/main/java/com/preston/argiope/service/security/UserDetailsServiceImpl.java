package com.preston.argiope.service.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.preston.argiope.model.user.User;
import com.preston.argiope.service.user.UserService;

/**
 * The only purpose of this class is to load a user as requested by the
 * {@link DaoAuthenticationProvider} (which we extend with our
 * {@link CustomAuthenticationProvider}). This class simply delegates to our
 * actual service layer to get the user. This keeps our service layer decoupled
 * from Spring Security.
 * 
 * @author pbriggs
 *
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {
	@Autowired private UserService userService;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userService.getUser(username);
		if(user == null)
			throw new UsernameNotFoundException("Username not found!");
		
		return user;
	}
}
