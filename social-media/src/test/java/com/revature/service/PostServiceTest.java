package com.revature.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.revature.dao.PostDao;
import com.revature.entity.PostEntity;
import com.revature.entity.UserEntity;
//import com.revature.exceptions.ApplicationException;
import com.revature.services.PostService;

@ExtendWith(MockitoExtension.class)
public class PostServiceTest 
{
	@InjectMocks
	PostService ps = new PostService();
	
	@Mock
	PostDao dao;
	
	int intTest=2;
	UserEntity testUser = new UserEntity(2,"wpruett@test.com","12345","Wes","Pruett", "Hi I am Wes! Thanks for visiting my profile!");
	List<PostEntity> testComments= new ArrayList<PostEntity>();
	//testComments.add("blag");
	PostEntity[] dummy= 
	{ 
			new PostEntity( 3,"Test 1", "Test", testComments,testUser)

	};
	        
	String test1="Mister";
			
	String test2="Test";
	@Test
	public void testSeeFirst()
	{
		when(dao.seeFirst(intTest)).thenReturn(dummy);
		
		PostEntity[] expected = dummy;
		PostEntity[] actual = null;
		
		actual= ps.seeFirst(2);
	
		assertEquals(expected, actual);
	}
}
