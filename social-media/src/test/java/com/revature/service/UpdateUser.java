package com.revature.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.revature.dao.UserDao;
import com.revature.entity.UserEntity;
import com.revature.services.UserService;



@ExtendWith(MockitoExtension.class)
public class UpdateUser {



    @InjectMocks
    UserService userService= new UserService();

    @Mock
    UserDao userDao ;

    

    

    @Test
    void testUpdateLast() {

        Optional<UserEntity> dummy = Optional.ofNullable(new UserEntity(100, "test@test.com","password","test", "user","test"));

        

        String lastNameTest="userss";
        dummy.get().setLastName(lastNameTest);
        userService.save(dummy.get());
        when(userDao.findByLastName( lastNameTest)).thenReturn(dummy);

        Optional<UserEntity> expected = dummy;
        Optional<UserEntity> actual =null;


        actual= userDao.findByLastName("userss");

        assertEquals(expected, actual);
    }

    

    @Test
    void testUpdateFirst() {
    Optional<UserEntity> dummy = Optional.ofNullable(new UserEntity(100, "test@test.com","password","test", "user","test"));

        String firstNameTest="userss";
        dummy.get().setFirstName(firstNameTest);
        userService.save(dummy.get());
        when(userDao.findByFirstName( firstNameTest)).thenReturn(dummy);
        
        Optional<UserEntity> expected = dummy;
        Optional<UserEntity> actual =null;
       
        actual= userDao.findByFirstName("userss");

        assertEquals(expected, actual);       
    }

    

    @Test
    void testUpdateEmail() {

    Optional<UserEntity> dummy = Optional.ofNullable(new UserEntity(100, "test@test.com","password","test", "user","test"));
        
        String emailTest="user@user.com";
   
        dummy.get().setEmail(emailTest);
        userService.save(dummy.get());
        when(userDao.findByEmailAndPassword( emailTest,"password" )).thenReturn(dummy);

        

        Optional<UserEntity> expected = dummy;
        Optional<UserEntity> actual =null;

        actual= userDao.findByEmailAndPassword(emailTest,"password" );

        assertEquals(expected, actual);     
    }

    

    





}