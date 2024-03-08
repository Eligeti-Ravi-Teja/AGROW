package com.jsp.Agrow.dao.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.jsp.Agrow.dao.utils.ResponseStructure;

@RestControllerAdvice
public class PostExceptionHandler {
	@ExceptionHandler(PostNotFound.class)
	public ResponseEntity<ResponseStructure<String>> PostNotFoundHandle(PostNotFound p){
		ResponseStructure<String> rs= new ResponseStructure<String>();
		rs.setMessage("Post Not Found");
		rs.setStatus(HttpStatus.BAD_REQUEST.value());
		rs.setData(p.getMsg());
		return new ResponseEntity<ResponseStructure<String>>(rs,HttpStatus.BAD_REQUEST);
	}

}
