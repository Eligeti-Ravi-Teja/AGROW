package com.jsp.Agrow.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jsp.Agrow.entity.Post;

public interface PostRepo extends JpaRepository<Post, Integer>{

}
