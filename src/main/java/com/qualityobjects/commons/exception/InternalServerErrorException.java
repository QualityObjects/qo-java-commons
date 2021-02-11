package com.qualityobjects.commons.exception;

public class InternalServerErrorException extends QOException {

	private static final long serialVersionUID = 5207795630755628143L;

	public InternalServerErrorException(String mensaje) {
		super(500 /*INTERNAL_SERVER_ERROR*/, mensaje);
	}

}
