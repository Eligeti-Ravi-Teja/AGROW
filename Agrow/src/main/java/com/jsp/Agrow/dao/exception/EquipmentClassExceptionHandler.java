package com.jsp.Agrow.dao.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.jsp.Agrow.dao.utils.ResponseStructure;

@RestControllerAdvice
public class EquipmentClassExceptionHandler {
	@ExceptionHandler(EquipmentNotFound.class)
	public ResponseEntity<ResponseStructure<String>> equipHandle(EquipmentNotFound e){
		ResponseStructure<String> rs=new ResponseStructure<String>();
		rs.setData(e.getMsg());
		rs.setMessage("Equipment does not exist");
		rs.setStatus(HttpStatus.NOT_ACCEPTABLE.value());
		return new ResponseEntity<ResponseStructure<String>>(rs,HttpStatus.NOT_ACCEPTABLE);
	}

}
