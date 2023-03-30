package com.fintechapp.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.fintechapp.DTO.LoginDTo;
import com.fintechapp.DTO.SignUpDto;
import com.fintechapp.entities.LoginEntity;
import com.fintechapp.entities.UserEntity;

@Service
public interface UserService {

	public String createUser(SignUpDto userDto) throws Exception;
	
	public List <UserEntity> viewAllUsers()throws Exception;
	public String emailVerification (String confirmationToken)throws Exception;
	public List <LoginEntity> viewLogers() throws Exception;
	
	public LoginEntity loggin (LoginDTo loginDTO)throws Exception;
	
}
