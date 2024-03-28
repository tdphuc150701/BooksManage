package com.bookmanage.service;

import com.bookmanage.dto.UserDTO;
import com.bookmanage.entity.UserEntity;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public interface AuthenticationService {
	UserDetails loadUserByUsername(String username) throws UsernameNotFoundException;

	String authenticate(String username, String password) throws Exception;

	UserEntity register(UserDTO userDTO);
}
