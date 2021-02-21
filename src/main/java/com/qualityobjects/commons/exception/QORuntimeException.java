package com.qualityobjects.commons.exception;

@SuppressWarnings("serial")
public class QORuntimeException extends RuntimeException {
	private final int httpStatus;

	public QORuntimeException(String msg) {
		this(QOException.DEFAULT_APP_ERROR_STATUS_CODE, msg);
	}

	public QORuntimeException(int httpStatis, String msg) {
		super(msg);
		this.httpStatus = httpStatis;
	}

	public QORuntimeException(String msg, Throwable rootCause) {
		this(QOException.DEFAULT_APP_ERROR_STATUS_CODE, msg, rootCause);
	}

	public QORuntimeException(int httpStatis, String msg, Throwable rootCause) {
		super(msg, rootCause);
		this.httpStatus = httpStatis;
	}

	public int getHttpStatus() {
		return httpStatus;
	}

}
