package com.example.demo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.example.demo.model.RegisterBean;
import com.example.demo.repository.UserRepository;

public class CustomDetailsServices implements UserDetailsService {
	@Autowired
	private UserRepository repo;
	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		RegisterBean user=repo.findByEmail(email);
		if(user==null) {
			throw new UsernameNotFoundException("user not found");
		}
		return new CustomDetails(user);
	}
	
}
