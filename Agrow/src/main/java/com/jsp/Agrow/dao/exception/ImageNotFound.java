package com.jsp.Agrow.dao.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ImageNotFound extends RuntimeException{
	private String msh="";

}
