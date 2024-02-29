package com.jsp.Agrow.dao.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.jsp.Agrow.dao.utils.ResponseStructure;

@RestControllerAdvice
public class UserExceptionHandler {
//	======================================================================================
	@ExceptionHandler(EmailAlreadyRegistered.class)
	public ResponseEntity<ResponseStructure<String>> handleExistEmail(EmailAlreadyRegistered e){
		ResponseStructure<String> rs=new ResponseStructure<String>();
		rs.setData(e.getMsg());
		rs.setStatus(HttpStatus.CONFLICT.value());
		rs.setMessage("Please try to login or enter another email!!");
		return new ResponseEntity<ResponseStructure<String>>(rs,HttpStatus.CONFLICT);
	}
	
//===========================================================================================
	
//	======================================================================================
	@ExceptionHandler(EmailDoesNotExist.class)
	public ResponseEntity<ResponseStructure<String>> handleIncorrectEmail(EmailDoesNotExist e){
		ResponseStructure<String> rs=new ResponseStructure<String>();
		rs.setData(e.getMsg());
		rs.setStatus(HttpStatus.NOT_FOUND.value());
		rs.setMessage("Incorrect email or please try to register before login!!");
		return new ResponseEntity<ResponseStructure<String>>(rs,HttpStatus.NOT_FOUND);
	}
//	======================================================================================
	
//	=====================================================================================
	@ExceptionHandler(PasswordIncorrect.class)
	public ResponseEntity<ResponseStructure<String>> handleIncorrectPassword(PasswordIncorrect e){
		ResponseStructure<String> rs=new ResponseStructure<String>();
		rs.setData(e.getMsg());
		rs.setStatus(HttpStatus.NOT_FOUND.value());
		rs.setMessage("Incorrect password please try again!!");
		return new ResponseEntity<ResponseStructure<String>>(rs,HttpStatus.NOT_FOUND);

	}
//	======================================================================================

}
