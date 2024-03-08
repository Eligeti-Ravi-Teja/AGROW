package com.jsp.Agrow.dao.utils;



//import org.springframework.http.HttpHeaders;

import lombok.Data;


@Data
//@NoArgsConstructor
public class ResponseStructure<T> {
	
	private String message;
	private int status;
	private T data;
}
