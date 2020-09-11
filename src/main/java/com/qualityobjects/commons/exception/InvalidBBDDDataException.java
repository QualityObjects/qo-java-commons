package com.qualityobjects.commons.exception;

public class InvalidBBDDDataException extends QOException {

	private static final long serialVersionUID = 5207795630755628143L;

	public InvalidBBDDDataException(String mensaje) {
		super(ErrorCodes.INVALID_DATA, mensaje);
	}

}
