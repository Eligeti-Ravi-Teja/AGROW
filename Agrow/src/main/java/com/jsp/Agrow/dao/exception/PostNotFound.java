package com.jsp.Agrow.dao.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PostNotFound extends RuntimeException{
	String msg="";

}
