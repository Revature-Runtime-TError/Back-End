package com.revature.service;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import com.revature.entity.UserEntity;
import com.revature.services.UserService;

@ExtendWith(MockitoExtension.class)
public class AuthService 
{
	@InjectMocks
	UserService us = new UserService();
	
	Optional<UserEntity> dummy = Optional.ofNullable(new UserEntity(2,"wpruett@test.com","12345","Wes","Pruett", "Hi I am Wes! Thanks for visiting my profile!"));

	@Test
	public void testAuthService()
	{
		
	}

}
