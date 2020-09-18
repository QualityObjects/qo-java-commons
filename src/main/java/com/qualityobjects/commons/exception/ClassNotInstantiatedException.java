package com.qualityobjects.commons.exception;

public class ClassNotInstantiatedException extends QORuntimeException {

	private static final long serialVersionUID = 5207795630755628143L;

	public ClassNotInstantiatedException() {
		this("Class can not be instantiated: ");
	}

	public ClassNotInstantiatedException(String msg) {
		super("Class can not be instantiated: "+ msg);
	}

}
