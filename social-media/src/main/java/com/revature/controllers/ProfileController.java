package com.revature.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.revature.annotations.Authorized;
import com.revature.dao.UserDao;
import com.revature.dtos.ViewAccountInput;
import com.revature.entity.PostEntity;
import com.revature.entity.UserEntity;
import com.revature.services.PostService;
import com.revature.services.UserService;

@RestController
@RequestMapping("/profile")
@CrossOrigin(origins = "http://localhost:4200", allowCredentials = "true")
public class ProfileController {

	@Autowired 
	UserService userService;
	
	@Autowired
	UserDao userDao;
	
	@Autowired
	PostService postService;
	
	
	@PutMapping("/edit")
	public UserEntity updateUser(@RequestBody UserEntity user) {
		
		return userService.UpdateUser(user);
		
	}
	
	@PostMapping("/viewprofile")
	public Optional <UserEntity> viewAccount(@RequestBody ViewAccountInput viewAccountInput) {
	return userService.fetchUser(viewAccountInput.getFirstname(), viewAccountInput.getLastname());
	
//			
	}
	@GetMapping("/viewprofile/{uid}")
	 public ResponseEntity<PostEntity[]> seeAuthorPost(@PathVariable("uid") int authorId) {
	    	return ResponseEntity.ok(this.postService.seeAuthorPost( authorId));
	    }
}
