package com.revature.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.revature.dao.UserDao;
import com.revature.entity.UserEntity;
import com.revature.services.UserService;

public class UpdateUser {

	@Autowired 
	UserService userService;
	@Autowired
	UserDao userDao;
	
	@Test
	void contextLoads() {
	}
	@Test
	void testUpdateLast() {
		UserEntity user =  new UserEntity(100, "a","b","c","d", "d");
           
	    assertThat(user).isNotNull();
	    
	    user.setLastName("abc");
	    userDao.save(user);
	    
	    Optional<UserEntity> optionalEntity =  userDao.findByLastName("abc");
	    UserEntity checkLastname= optionalEntity.get();
	    assertThat(checkLastname).isNotNull();
	    
 
	}
	
	@Test
	void testUpdateFirst() {
		UserEntity user =  new UserEntity(100, "a","b","c","d", "d");
           
	    assertThat(user).isNotNull();
	    
	    user.setFirstName("abc");
	    userDao.save(user);
	    
	    Optional<UserEntity> optionalEntity =  userDao.findByFirstName("abc");
	    UserEntity checkFirstname= optionalEntity.get();
	    assertThat(checkFirstname).isNotNull();
	    
 
	}
	
	@Test
	void testUpdateEmail() {
		UserEntity user =  new UserEntity(100, "a","b","c","d", "d");
           
	    assertThat(user).isNotNull();
	    
	    user.setEmail("email");
	    user.setPassword("password");
	    userDao.save(user);
	    
	    Optional<UserEntity> optionalEntity =  userDao.findByEmailAndPassword("email","password" );
	    UserEntity checkFirstname= optionalEntity.get();
	    assertThat(checkFirstname).isNotNull();
	    
 
	}
}
