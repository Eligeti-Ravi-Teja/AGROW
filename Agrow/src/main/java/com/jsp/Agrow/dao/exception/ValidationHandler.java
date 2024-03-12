package com.jsp.Agrow.dao.exception;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.jsp.Agrow.dao.utils.ResponseStructure;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;

@RestControllerAdvice
public class ValidationHandler extends ResponseEntityExceptionHandler{

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatusCode status, WebRequest request) {
		List<ObjectError> error = ex.getAllErrors();
		Map<String, String> map = new HashMap<String, String>();
		ResponseStructure<Object> structure = new ResponseStructure<>();
		for (ObjectError objectError : error) {
			String filedName = ((FieldError) objectError).getField();
			String message = ((FieldError) objectError).getDefaultMessage();
			map.put(filedName, message);

		}
		structure.setMessage("provide valid details");
		structure.setStatus(HttpStatus.NOT_FOUND.value());
		structure.setData(map);
		return new ResponseEntity<Object>(structure, HttpStatus.BAD_REQUEST);
	}


	@org.springframework.web.bind.annotation.ExceptionHandler(ConstraintViolationException.class)
	public ResponseEntity<ResponseStructure<Object>> handleConstraintViolationException(ConstraintViolationException ex) {
		ResponseStructure<Object> structure = new ResponseStructure<Object>();
		Map<String, String> map = new HashMap<String, String>();

		for (ConstraintViolation<?> violation : ex.getConstraintViolations()) {
			String field = violation.getPropertyPath().toString();
			String message = violation.getMessage();
			map.put(field, message);

		}

		structure.setMessage("provide proper details");
		structure.setStatus(HttpStatus.NOT_FOUND.value());
		structure.setData(map);

		return new ResponseEntity<ResponseStructure<Object>>(structure, HttpStatus.BAD_REQUEST);

	}
}
