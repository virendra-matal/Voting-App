package com.veer.voat.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.veer.voat.Dao.UserRepository;
import com.veer.voat.model.User;

public class UserDetailsServiceImpl implements UserDetailsService{

	@Autowired
	private UserRepository userRepo;
	
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		try {
			User user = userRepo.findByUsername(username);
			UserDetailsImpl userDetailsImpl = new UserDetailsImpl(user);
			return userDetailsImpl;
		} catch (Exception e) {
			e.printStackTrace();
			throw new UsernameNotFoundException("User not found with username "+username);
		}
		
	}

}
