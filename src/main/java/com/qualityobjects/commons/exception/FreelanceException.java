package com.qualityobjects.commons.exception;

public class FreelanceException extends QOException{
	private static final long serialVersionUID = 5207795630755628143L;

	public FreelanceException() {
		this("El usuario al que intentas crear vacaciones es autónomo");
	}

	public FreelanceException(String msg) {
		super(QOException.DEFAULT_APP_ERROR_STATUS_CODE, msg);
	}
}
