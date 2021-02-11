package com.qualityobjects.commons.exception;

public class AccessDeniedException extends QOException {

	private static final long serialVersionUID = 5207795630755628143L;

	public AccessDeniedException() {
		super(403, ErrorCodes.ACCESS_DENIED, "Acceso denegado");
	}

}
