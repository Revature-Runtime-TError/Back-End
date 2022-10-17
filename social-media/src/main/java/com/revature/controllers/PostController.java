package com.revature.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.revature.annotations.Authorized;
import com.revature.entity.PostEntity;
import com.revature.entity.UserEntity;
import com.revature.services.PostService;
import com.revature.services.UserService;

@RestController
@RequestMapping("/post")
@CrossOrigin(origins = "http://localhost:4200", allowCredentials = "true")
public class PostController {

	private final PostService postService;
	
	@Autowired UserService userService;
	
    public PostController(PostService postService) {
        this.postService = postService;
    }
 
    //@Authorized
    @GetMapping
    public ResponseEntity<List<PostEntity>> getAllPosts() {
    	return ResponseEntity.ok(this.postService.getAll());
    }
    
    @Authorized
    @PutMapping
    public ResponseEntity<PostEntity> upsertPost(@RequestBody PostEntity post) {
    	return ResponseEntity.ok(this.postService.upsert(post));
    }
//    
    @GetMapping("/fetched/{firstName}/{lastName}")
    public ResponseEntity<Optional<UserEntity>> findAuthorByName(
    @PathVariable("firstName")String firstName,
    @PathVariable("lastName")String lastName)
    {
    	System.out.println(firstName+lastName);
    	return ResponseEntity.ok(this.userService.findByName(firstName,lastName));
    }
    
    //Service method to call see first functionality
    //@Authorized
    @GetMapping("/seeFirst/{uid}")
    public ResponseEntity<PostEntity[]> seeFirst(@PathVariable("uid") int authorId)
    {
    	@SuppressWarnings("unused")
		Optional<UserEntity> author = Optional.of(new UserEntity());
    	
		author = userService.findById(authorId);
    	
		return ResponseEntity.ok(this.postService.seeFirst(authorId));
    }
    
    
    
}
