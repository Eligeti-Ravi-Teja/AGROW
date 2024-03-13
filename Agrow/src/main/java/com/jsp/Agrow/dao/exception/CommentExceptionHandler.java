package com.jsp.Agrow.dao.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.jsp.Agrow.dao.utils.ResponseStructure;

@RestControllerAdvice
public class CommentExceptionHandler {
	
	public ResponseEntity<ResponseStructure<String>> noComment(CommentNotFound e){
		ResponseStructure<String> rs=new ResponseStructure<String>();
		rs.setData(e.getMsg());
		rs.setMessage("No comment found");
		rs.setStatus(HttpStatus.BAD_REQUEST.value());
		return new ResponseEntity<ResponseStructure<String>>(rs,HttpStatus.BAD_REQUEST);
		
	}

}
