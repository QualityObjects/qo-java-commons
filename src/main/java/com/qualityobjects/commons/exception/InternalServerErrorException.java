package com.qualityobjects.commons.exception;

import org.springframework.http.HttpStatus;

public class InternalServerErrorException extends QOException {

	private static final long serialVersionUID = 5207795630755628143L;

	public InternalServerErrorException(String mensaje) {
		super(HttpStatus.INTERNAL_SERVER_ERROR.value(), mensaje);
	}

}
