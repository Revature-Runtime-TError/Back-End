package com.revature.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.revature.dao.UserDao;
import com.revature.entity.UserEntity;
import com.revature.services.UserService;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest 
{
	@InjectMocks
	UserService us = new UserService();
	
	@Mock 
	UserDao dao;
	
	Optional<UserEntity> dummy = Optional.ofNullable(new UserEntity(2,"wpruett@test.com","12345","Wes","Pruett", "Hi I am Wes! Thanks for visiting my profile!"));
	
	String firstNameTest="Wes";
	String lastNameTest="Pruett";
	@Test
	public void testFindByName()
	{
		when(dao.findByFirstNameAndLastName(firstNameTest, lastNameTest)).thenReturn(dummy);
		
		Optional<UserEntity> expected = dummy;
		Optional<UserEntity> actual =null;
		
		actual= us.findByName("Wes", "Pruett");
		
		assertEquals(expected, actual);
	}
}
