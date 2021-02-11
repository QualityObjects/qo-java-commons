package com.qualityobjects.commons.exception;

public class InvalidInputDataException extends QOException {

	private static final long serialVersionUID = 5207795630755628143L;

	public InvalidInputDataException() {
		super(QOException.DEFAULT_APP_ERROR_STATUS_CODE, ErrorCodes.INVALID_DATA, "Los datos de entrada no son válidos");
	}

	public InvalidInputDataException(String msg) {
		super(QOException.DEFAULT_APP_ERROR_STATUS_CODE, ErrorCodes.INVALID_DATA, msg);
	}

}
