package com.bookmanage.converter;

import com.bookmanage.dto.UserDTO;
import com.bookmanage.entity.UserEntity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class UserConverter {
	@Autowired
	private PasswordEncoder bcryptEncoder;

	public UserDTO toDto(UserEntity user) {
		UserDTO dto = new UserDTO();
		dto.setEmail(user.getEmail());
		dto.setUsername(user.getUsername());
		return dto;
	}

	public UserEntity toEntity(UserDTO userDTO) {
		UserEntity user = new UserEntity();
		user.setEmail(userDTO.getEmail());
		user.setUsername(userDTO.getUsername());
		user.setPassword(bcryptEncoder.encode(userDTO.getPassword()));
		return user;
	}

}