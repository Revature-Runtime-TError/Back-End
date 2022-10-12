package com.revature.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.revature.entity.PostEntity;
import com.revature.entity.UserEntity;

public interface PostDao extends JpaRepository<PostEntity, Integer>{


	//Created a Native query to a list all post with selected Author's post always first
	//@Query(value ="SELECT * FROM POSTS order by AUTHOR_ID not like "+authorId+", Author_id;", nativeQuery = true)
	@Query(value ="SELECT * FROM POSTS order by AUTHOR_ID not like :authorId, AUTHOR_ID;", nativeQuery = true)
	PostEntity[] seeFirst(@Param("authorId") int authorId);
}
