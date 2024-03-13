package com.jsp.Agrow.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jsp.Agrow.dao.utils.ResponseStructure;
import com.jsp.Agrow.entity.Comment;
import com.jsp.Agrow.service.CommentService;

@RestController
public class CommentController {
	
	@Autowired
	CommentService service;
//	===================comment=========================================================
	@PostMapping("/comment")
	public ResponseEntity<ResponseStructure<Comment>> comment(@RequestParam int pid,@RequestParam int uid,@RequestParam String msg){
		return service.makeComment(pid, uid, msg);
	}
//	================================================================================

//	=======================deleteComment================================================
	@DeleteMapping("/deleteComment")
	public ResponseEntity<ResponseStructure<Comment>> deleteComment(@RequestParam int id){
		return service.deleteComment(id);
	}
}
