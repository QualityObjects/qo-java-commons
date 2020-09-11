package com.qualityobjects.commons.exception;

import org.springframework.http.HttpStatus;

public class InvalidTokenException extends QOException {

	private static final long serialVersionUID = 5207795630755628143L;

	public InvalidTokenException() {
		super(HttpStatus.UNAUTHORIZED.value(), ErrorCodes.INVALID_TOKEN, "Sesion caducada.");
	}

}
