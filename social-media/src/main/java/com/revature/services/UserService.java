package com.revature.services;

import com.revature.dao.UserDao;
import com.revature.entity.UserEntity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

	@Autowired
    private UserDao userDao;

	public UserService() 
	{	
	}
    public UserService(UserDao userDao) {
        this.userDao = userDao;
    }

    public Optional<UserEntity> findByCredentials(String email, String password) {
        return userDao.findByEmailAndPassword(email, password);
    }

    public Optional<UserEntity> findById(int userId) {
        return userDao.findById(userId);
    }
   
    
    public UserEntity save(UserEntity user) {
        return userDao.save(user);
    }

	public Optional<UserEntity> findByName(String firstName, String lastName) {
		System.out.println(userDao.findByFirstNameAndLastName(firstName, lastName));
		return userDao.findByFirstNameAndLastName(firstName, lastName);
	}
    
	public UserEntity UpdateUser(UserEntity user) {
		
		return userDao.save(user);
		
	}
   
}
