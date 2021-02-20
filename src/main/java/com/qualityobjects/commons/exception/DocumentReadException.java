package com.qualityobjects.commons.exception;

public class DocumentReadException extends QOException {

	private static final long serialVersionUID = 5207795630755628143L;

	public DocumentReadException() {
		this("Error leyendo documento");
	}

	public DocumentReadException(String mensaje) {
		super(ErrorCodes.DOCUMENT_ERROR, mensaje);
	}

}
