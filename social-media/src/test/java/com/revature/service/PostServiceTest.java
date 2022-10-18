package com.revature.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import com.revature.dao.PostDao;
import com.revature.entity.PostEntity;
import com.revature.entity.UserEntity;
//import com.revature.exceptions.ApplicationException;
import com.revature.services.PostService;

@ExtendWith(MockitoExtension.class)
public class PostServiceTest {
	@InjectMocks
	PostService ps = new PostService();

	@Mock
	PostDao dao;

	int intTest = 2;
	UserEntity testUser = new UserEntity(2, "wpruett@test.com", "12345", "Wes", "Pruett",
			"Hi I am Wes! Thanks for visiting my profile!");
	List<PostEntity> testComments = new ArrayList<PostEntity>();
	// testComments.add("blag");
	PostEntity[] dummy = { new PostEntity(3, "Test 1", "Test", testComments, testUser)

	};

	String test1 = "Mister";

	String test2 = "Test";
	List<PostEntity> allcomments= new ArrayList<PostEntity>();
	@Test
	public void testSeeFirst() {
		when(dao.seeFirst(intTest)).thenReturn(dummy);

		PostEntity[] expected = dummy;
		PostEntity[] actual = null;

		actual = ps.seeFirst(2);

		assertEquals(expected, actual);
	}

	@Test
	public void testSeeAuthorPost() {
		int input = 0;

		UserEntity author = new UserEntity(0, "Post", "Author", "d", "d", "d");
		PostEntity[] fakePost = { new PostEntity(0, "Test 1", "Test", testComments, testUser)

		};

		when(dao.seeAuthorPost(input)).thenReturn(fakePost);

		PostEntity[] fakestPost = ps.seeAuthorPost(input);
		assertEquals(fakePost[0], fakestPost[0]);

	}

	@Test
	public void testSeeAuthorPostIdNotInDataBase() {
		UserEntity testUser = new UserEntity(1, "d", "d", "d", "d", "d");
		int input = 0;
		when(dao.seeAuthorPost(input)).thenReturn(null);

		try {
			ps.seeAuthorPost(input);
		} catch (ResponseStatusException e) {
			assertEquals(e.getStatus(), HttpStatus.NOT_FOUND);
		}

	}
		@Test
		public void testgetAll() {
			when(dao.findAll()).thenReturn(allcomments);
			
			List<PostEntity> expected = allcomments;
			List<PostEntity> actual = null;
			
			actual= ps.getAll();
		
			assertEquals(expected, actual);			
		}
		
		@Test 
		public void testUpsert() {
			Optional<PostEntity> temp=Optional.ofNullable(new PostEntity( 100,"Test 1", "Test", testComments,testUser));
			
			ps.upsert(temp.get());
			when(dao.findById(100)).thenReturn(temp);
			
			Optional<PostEntity> expected = temp;
			Optional<PostEntity> actual =null;
			
			actual= dao.findById(100);
			
			assertEquals(expected, actual);
			
		}


}


