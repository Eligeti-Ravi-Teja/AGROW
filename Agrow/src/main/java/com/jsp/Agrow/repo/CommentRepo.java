package com.jsp.Agrow.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jsp.Agrow.entity.Comment;

public interface CommentRepo extends JpaRepository<Comment, Integer>{

}
