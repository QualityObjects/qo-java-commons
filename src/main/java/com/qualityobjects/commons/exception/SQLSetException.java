package com.qualityobjects.commons.exception;

public class SQLSetException extends QORuntimeException {

	private static final long serialVersionUID = 5207795630755628143L;

	public SQLSetException() {
		this("Invalid SQL data: ");
	}

	public SQLSetException(String msg) {
		super("Invalid SQL data: "+ msg);
	}

}
