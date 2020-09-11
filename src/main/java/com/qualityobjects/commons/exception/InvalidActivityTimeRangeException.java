package com.qualityobjects.commons.exception;

import org.springframework.http.HttpStatus;

public class InvalidActivityTimeRangeException extends QOException {

	private static final long serialVersionUID = 5207795630755628143L;

	public InvalidActivityTimeRangeException() {
		this("El horario de la actividad no puede solaparse con otra actividad existente");
	}
	public InvalidActivityTimeRangeException(String reason) {
		super(HttpStatus.CONFLICT.value(), ErrorCodes.INVALID_DATA, reason);
	}

}
