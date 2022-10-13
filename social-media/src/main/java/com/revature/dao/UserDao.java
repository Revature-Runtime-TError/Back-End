package com.revature.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.revature.entity.UserEntity;

import java.util.Optional;

public interface UserDao extends JpaRepository<UserEntity, Integer> {

    Optional<UserEntity> findByEmailAndPassword(String email, String password);

	//Optional<UserEntity> findByLastName(String authorLastName);

	Optional<UserEntity> findByFirstNameAndLastName(String firstName, String lastName);

	Optional<UserEntity> findByLastName(String authorLastName);

	Optional<UserEntity> findByFirstName(String string);


}
