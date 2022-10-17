package com.revature.services;

import com.revature.dao.UserDao;
import com.revature.entity.UserEntity;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Service
public class UserService {

	@Autowired
	private UserDao userDao;

	public UserService() {
	}

	public Optional<UserEntity> findByCredentials(String email, String password) {
		return userDao.findByEmailAndPassword(email, password);
	}

	public Optional<UserEntity> findByEmail(String email) {
		return userDao.findByEmail(email);
	}

	public UserService(UserDao userDao) {
		this.userDao = userDao;
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

	public UserEntity resetPassword(UserEntity user) {
		return userDao.save(user);

	}

	public Optional<UserEntity> fetchUser(String firstName, String lastName) {
		Optional<UserEntity> user = userDao.findByFirstNameAndLastName(firstName, lastName);

		if (user == null) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "account does not exist");
		}

		return user;

	}

}
