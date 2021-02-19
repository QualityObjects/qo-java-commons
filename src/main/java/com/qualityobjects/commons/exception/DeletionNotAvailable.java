package com.qualityobjects.commons.exception;

public class DeletionNotAvailable extends QOException {

	private static final long serialVersionUID = 5207795635628143L;

	public DeletionNotAvailable() {
		this("No es posible borrar el elemento");
	}

	public DeletionNotAvailable(String msg) {
		super(412 /* Precondition Failed */, ErrorCodes.INVALID_DATA, msg);
	}

}
