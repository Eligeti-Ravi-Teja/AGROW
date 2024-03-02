package com.jsp.Agrow.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jsp.Agrow.entity.Comment;
import com.jsp.Agrow.repo.CommentRepo;

@Repository
public class CommentDao {
	@Autowired
	CommentRepo repo;
	
//	=====insert Image===========================================================
	public Comment createComment(Comment comment) {
		return repo.save(comment);
	}
//	==========================================================================
	
//	==================================update==========================================
	public Comment updateComment(Comment comment) {
		return repo.save(comment);
	}
//	=============================================================================
	
//============================Fetch by id===============================================
	public Comment findCommentById(int id) {
		Optional<Comment> s = repo.findById(id);
		if(s.isPresent()) {
			return s.get();
		}
		else {
			return null;
		}
	}
//	===================================================================================
	
//	===============================fetchAll===========================================
	public List<Comment> FetchAll(){
		return repo.findAll();
	}
//	=====================================================================================

//	============================delete by id =========================================
	public Comment deleteComment(int id) {
		Optional<Comment> data = repo.findById(id);
		repo.deleteById(id);
		return data.get();
	}
}
