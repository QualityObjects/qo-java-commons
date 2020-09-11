package com.qualityobjects.commons.exception;

import org.springframework.http.HttpStatus;

public class AccessDeniedException extends QOException {

	private static final long serialVersionUID = 5207795630755628143L;

	public AccessDeniedException() {
		super(HttpStatus.FORBIDDEN.value(), ErrorCodes.ACCESS_DENIED, "Acceso denegado");
	}

}
