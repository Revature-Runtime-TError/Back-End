package com.revature.service;



import static org.junit.jupiter.api.Assertions.assertEquals;

import static org.mockito.Mockito.when;



import java.util.Optional;



import org.junit.jupiter.api.Test;

import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.InjectMocks;

import org.mockito.Mock;

import org.mockito.junit.jupiter.MockitoExtension;



import com.revature.dao.UserDao;

import com.revature.entity.UserEntity;

import com.revature.services.AuthService;

import com.revature.services.UserService;



@ExtendWith(MockitoExtension.class)



public class AuthServiceTest {

    @InjectMocks
    UserService us= new  UserService();

    @InjectMocks
    AuthService as = new AuthService(us);

    
    @Mock
    UserDao dao;

    Optional<UserEntity> dummy = Optional.ofNullable(new UserEntity(100, "test@test.com","password","test", "user", "test"));
    String emailTest="user@user.com";
    String password="password";
    String firstNameTest="test";
    String lastNameTest="user";
    
    @Test
    public void testFindCreditialsl() {

            dummy.get().setEmail(emailTest);
            dummy.get().setPassword(password);
            us.save(dummy.get());
            when(as.findByCredentials(emailTest, password)).thenReturn(dummy);

            Optional<UserEntity> expected = dummy;
            Optional<UserEntity> actual =null;

            actual= dao.findByEmailAndPassword(emailTest,"password" );
            assertEquals(expected, actual);
        }

    

    @Test

     public void testfindByName() {

        dummy.get().setFirstName(firstNameTest);
        dummy.get().setLastName(lastNameTest);

        us.save(dummy.get());
        when(as.findByName(firstNameTest, lastNameTest)).thenReturn(dummy);

        Optional<UserEntity> expected = dummy;
        Optional<UserEntity> actual =null;

        actual= dao.findByFirstNameAndLastName(firstNameTest, lastNameTest);

        assertEquals(expected, actual);
    }

    @Test
    public void testregister() {

        dummy.get().setFirstName(firstNameTest);
        as.register(dummy.get());
        when(dao.findByFirstName( firstNameTest)).thenReturn(dummy);

        Optional<UserEntity> expected = dummy;
        Optional<UserEntity> actual =null;

        actual= dao.findByFirstName(firstNameTest);

        assertEquals(expected, actual);
    }
}