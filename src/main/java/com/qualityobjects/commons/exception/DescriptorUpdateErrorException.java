package com.qualityobjects.commons.exception;

import org.springframework.http.HttpStatus;

public class DescriptorUpdateErrorException extends QOException {

	private static final long serialVersionUID = 5207795630755628143L;

	public DescriptorUpdateErrorException(String mensaje) {
		super(HttpStatus.FORBIDDEN.value(), ErrorCodes.DESCRIPTOR_UPDATE_ERROR, mensaje);
	}

}
