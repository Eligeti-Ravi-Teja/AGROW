package com.jsp.Agrow.dao.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.jsp.Agrow.dao.utils.ResponseStructure;

@RestControllerAdvice
public class ImageExceptionHandler {
	@ExceptionHandler(UserDoesNotExit.class)
	public ResponseEntity<ResponseStructure<String>> UserNotFound(UserDoesNotExit e){
		ResponseStructure<String> rs=new ResponseStructure<String>();
		rs.setData(e.getMsg());
		rs.setMessage("No user found");
		rs.setStatus(HttpStatus.NOT_ACCEPTABLE.value());
		return new ResponseEntity<ResponseStructure<String>>(rs,HttpStatus.NOT_ACCEPTABLE);
		
	}
	@ExceptionHandler(ImageNotFound.class)
	public ResponseEntity<ResponseStructure<String>> ImageNotFoundHadle(ImageNotFound e){
		ResponseStructure<String> rs=new ResponseStructure<String>();
		rs.setData(e.getMsh());
		rs.setMessage("image not found");
		rs.setStatus(HttpStatus.BAD_REQUEST.value());
		return new ResponseEntity<ResponseStructure<String>>(rs,HttpStatus.BAD_REQUEST);
	}

}
