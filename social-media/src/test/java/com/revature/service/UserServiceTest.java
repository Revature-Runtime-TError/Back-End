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

	
	Optional<UserEntity> dummy = Optional.ofNullable(new UserEntity(2,"wpruett@test.com","12345","Wes","Pruett", "Hi I am Wes! Thanks for visiting my profile!"));
	UserEntity dummy2 = new UserEntity(2,"wpruett@test.com","12345","Wes","Pruett", "Hi I am Wes! Thanks for visiting my profile!");
	String firstNameTest="Wes";
	String lastNameTest="Pruett";
	String email ="wes.j.pruett@gmail.com";
	String password="12345";
	int id=2;

	@Test
	public void testFindByName() {
		when(dao.findByFirstNameAndLastName(firstNameTest, lastNameTest)).thenReturn(dummy);

		Optional<UserEntity> expected = dummy;
		Optional<UserEntity> actual = null;

		actual = us.findByName("Wes", "Pruett");

		assertEquals(expected, actual);
	}

	
	 @Test
		void testFindId() {
			when(dao.findById(2)).thenReturn(dummy);
			
			Optional<UserEntity> expected = dummy;
			Optional<UserEntity> actual =null;
			
			actual= us.findById(id);
			
			assertEquals(expected, actual);
		}
	
	 
	 @Test
		void testFindByEmail() {
			when(dao.findByEmail("wes.j.pruett@gmail.com")).thenReturn(dummy);
			
			Optional<UserEntity> expected = dummy;
			Optional<UserEntity> actual =null;
			
			actual= us.findByEmail(email);
			
			assertEquals(expected, actual);
		}
	 
	 @Test
		void testFindByCredentials() {
			when(dao.findByEmailAndPassword("wes.j.pruett@gmail.com","12345")).thenReturn(dummy);
			
			Optional<UserEntity> expected = dummy;
			Optional<UserEntity> actual =null;
			
			actual= us.findByCredentials(email,password);
			
			assertEquals(expected, actual);
		}
	 
	   @Test
	    void testUpdateUser()
	    {
		   when(dao.save(dummy2)).thenReturn(dummy2);
		   
		   Optional<UserEntity> expected = dummy;
		   Optional<UserEntity> actual =null;
			
		   actual= Optional.ofNullable(us.UpdateUser(dummy2));
			
		   assertEquals(expected, actual);
	    }
	   
	   @Test
	   void testResetPassword()
	   {
		   when(dao.save(dummy2)).thenReturn(dummy2);
		   
		   Optional<UserEntity> expected = dummy;
		   Optional<UserEntity> actual =null;
			
		   actual= Optional.ofNullable(us.resetPassword(dummy2));
			
		   assertEquals(expected, actual);
	   }
	   

	   
	 @Test
	    void testUpdateLast() {

	        Optional<UserEntity> dummy = Optional.ofNullable(new UserEntity(100, "test@test.com","password","test", "user","test"));

	        

	        String lastNameTest="userss";
	        dummy.get().setLastName(lastNameTest);
	        us.save(dummy.get());
	        when(dao.findByLastName( lastNameTest)).thenReturn(dummy);

	        Optional<UserEntity> expected = dummy;
	        Optional<UserEntity> actual =null;


	        actual= dao.findByLastName("userss");

	        assertEquals(expected, actual);
	    }

	 

	    @Test
	    void testUpdateFirst() {
	    Optional<UserEntity> dummy = Optional.ofNullable(new UserEntity(100, "test@test.com","password","test", "user","test"));

	        String firstNameTest="userss";
	        dummy.get().setFirstName(firstNameTest);
	        us.save(dummy.get());
	        when(dao.findByFirstName( firstNameTest)).thenReturn(dummy);
	        
	        Optional<UserEntity> expected = dummy;
	        Optional<UserEntity> actual =null;
	       
	        actual= dao.findByFirstName("userss");

	        assertEquals(expected, actual);       
	    }
 

	    @Test
	    void testUpdateEmail() {

	    Optional<UserEntity> dummy = Optional.ofNullable(new UserEntity(100, "test@test.com","password","test", "user","test"));
	        
	        String emailTest="user@user.com";
	   
	        dummy.get().setEmail(emailTest);
	        us.save(dummy.get());
	        when(dao.findByEmailAndPassword( emailTest,"password" )).thenReturn(dummy);

	        

	        Optional<UserEntity> expected = dummy;
	        Optional<UserEntity> actual =null;

	        actual= dao.findByEmailAndPassword(emailTest,"password" );

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
