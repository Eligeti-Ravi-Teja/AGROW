package com.jsp.Agrow.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.jsp.Agrow.dao.utils.ResponseStructure;
import com.jsp.Agrow.entity.Post;
import com.jsp.Agrow.service.PostService;

import lombok.Getter;

@RestController
public class PostController {
	@Autowired
	PostService service;
	
//	======================createPost===================================================
	@PostMapping("/createPost")
	public ResponseEntity<ResponseStructure<Post>> createPost(@RequestParam int id,@RequestParam("img") MultipartFile file,String caption,String address) throws IOException{
		return service.postData(id, file, caption, address);
	}
//	===================================================================================
	
//	================fetchPost========================================================
	@GetMapping("/getPost")
	public ResponseEntity<ResponseStructure<Post>> getPost(@RequestParam int id){
		return service.getById(id);
	}

}