package com.jsp.Agrow.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jsp.Agrow.entity.Post;
import com.jsp.Agrow.repo.PostRepo;

@Repository
public class PostDao {
	@Autowired
	PostRepo repo;
	
//	=====insert Image===========================================================
	public Post createPost(Post post) {
		return repo.save(post);
	}
//	==========================================================================
	
//	==================================update==========================================
	public Post updatePost(Post post) {
		return repo.save(post);
	}
//	=============================================================================
	
//============================Fetch by id===============================================
	public Post findPostById(int id) {
		Optional<Post> s = repo.findById(id);
		if(s.isPresent()) {
			return s.get();
		}
		else {
			return null;
		}
	}
//	===================================================================================
	
//	===============================fetchAll===========================================
	public List<Post> FetchAll(){
		return repo.findAll();
	}
//	=====================================================================================

//	============================delete by id =========================================
	public Post deletePost(int id) {
		Optional<Post> data = repo.findById(id);
		repo.deleteById(id);
		return data.get();
	}
}
