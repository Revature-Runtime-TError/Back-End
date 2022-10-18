package com.revature.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import com.revature.dao.UserDao;
import com.revature.entity.UserEntity;
import com.revature.services.UserService;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {
	@InjectMocks
	UserService us = new UserService();

	@Mock
	UserDao dao;

	Optional<UserEntity> dummy = Optional.ofNullable(new UserEntity(2, "wpruett@test.com", "12345", "Wes", "Pruett",
			"Hi I am Wes! Thanks for visiting my profile!"));

	String firstNameTest = "Wes";
	String lastNameTest = "Pruett";

	@Test
	public void testFindByName() {
		when(dao.findByFirstNameAndLastName(firstNameTest, lastNameTest)).thenReturn(dummy);

		Optional<UserEntity> expected = dummy;
		Optional<UserEntity> actual = null;

		actual = us.findByName("Wes", "Pruett");

		assertEquals(expected, actual);
	}

	@Test
	public void testFetchUser() {
		Optional<UserEntity> fetched = Optional.ofNullable(new UserEntity(2, "d", "d", "Test", "User", "d"));
		String firstName = "Test";
		String lastName = "User";

		when(dao.findByFirstNameAndLastName(firstName, lastName)).thenReturn(fetched);

		Optional<UserEntity> fetchedAcc = null;

		fetchedAcc = us.fetchUser("Test", "User");

		assertEquals(fetchedAcc, fetched);

	}

	@Test
	public void testFetchUserNull() {
		String firstName = "Fake";
		String lastName = "Info";

		when(dao.findByFirstNameAndLastName(firstName, lastName)).thenReturn(null);

		try {
			us.fetchUser("Fake", "Info");
			fail();
		} catch (ResponseStatusException e) {
			assertEquals(e.getStatus(), HttpStatus.NOT_FOUND);
		}

	}

}
