package com.qualityobjects.commons.exception;

public class SubventionException extends QOException {

	private static final long serialVersionUID = 5207795630755628143L;

	public SubventionException(String mensaje) {
		super(QOException.DEFAULT_APP_ERROR_STATUS_CODE, ErrorCodes.DESCRIPTOR_UPDATE_ERROR, mensaje);
	}

}