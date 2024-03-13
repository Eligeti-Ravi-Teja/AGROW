package com.jsp.Agrow.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.jsp.Agrow.entity.Image;
import com.jsp.Agrow.entity.User;

public interface UserRepo extends JpaRepository<User, Integer>{
	
	@Query("select e from User e where email=?1")
	public User fetchByEmail(String email);
	
	@Query("select e from User e where image=?1")
	public User fetchUserByImage(Image image); 
	

}
