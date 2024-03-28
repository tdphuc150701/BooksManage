package com.bookmanage.service.impl;

import java.util.ArrayList;

import com.bookmanage.config.JwtTokenUtil;
import com.bookmanage.converter.UserConverter;
import com.bookmanage.dto.UserDTO;
import com.bookmanage.entity.UserEntity;
import com.bookmanage.repository.UserRepository;
import com.bookmanage.service.AuthenticationService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class JwtUserDetailsService implements AuthenticationService, UserDetailsService {
	@Autowired
	private AuthenticationManager authenticationManager;
	@Autowired
	private JwtTokenUtil jwtTokenUtil;
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private UserConverter userConverter;
	@Autowired
	private UserDetailsService userDetailsService;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UserEntity user = userRepository.findByUsername(username);
		if (user == null) {
			throw new UsernameNotFoundException("User not found with username: " + username);
		}
		return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(),
				new ArrayList<>());
	}

	public UserEntity register(UserDTO userDTO) {
		// Kiểm tra xem tên người dùng đã tồn tại chưa
//		if (userRepository.existsByUsername(userDTO.getUsername())) {
//				return null; // Trả về null nếu tên người dùng đã tồn tại
//			}
		UserEntity user = userConverter.toEntity(userDTO);
		user = userRepository.save(user);
		return user;
	}

	public String authenticate(String username, String password) throws Exception {
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
		} catch (DisabledException e) {
			throw new Exception("USER_DISABLED", e);
		} catch (BadCredentialsException e) {
			throw new Exception("INVALID_CREDENTIALS", e);
		}

		final UserDetails userDetails = userDetailsService.loadUserByUsername(username);
		return jwtTokenUtil.generateToken(userDetails);
	}

}
