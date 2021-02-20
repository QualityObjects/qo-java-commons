package com.qualityobjects.commons.exception;

/**
 * Unexpected error reading data from DB or other datasource
 */
public class DataReadRuntimeException extends QORuntimeException {

	private static final long serialVersionUID = 5207795630755628143L;

	public DataReadRuntimeException() {
		this("Unexpected error reading data");
	}

	public DataReadRuntimeException(String msg) {
		super(msg);
	}

}
