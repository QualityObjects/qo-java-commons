package com.qualityobjects.commons.exception;

import org.springframework.http.HttpStatus;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class QOException extends Exception {

	private static final long serialVersionUID = 4884095608979032433L;

	public static final int DEFAULT_APP_ERROR_STATUS_CODE = HttpStatus.I_AM_A_TEAPOT.value();
	
	private final int httpStatus;
	private final int code;
	
	/**
	 * Parámetros específicos del error
	 */
	private final ErrorParams errorData = new ErrorParams();
	
	
	public QOException() {		
		this(DEFAULT_APP_ERROR_STATUS_CODE, 0, "QO exception", null, null);
	}
	
	public QOException(final int code, final String msg) {
		this(DEFAULT_APP_ERROR_STATUS_CODE, code, msg, null, null);
	}

	public QOException(final int code, final String msg, final Throwable cause) {
		this(DEFAULT_APP_ERROR_STATUS_CODE, code, msg, null, cause);
	}

	public QOException(final int httpStatus, final int code, final String msg) {
		this(httpStatus, code, msg, null, null);
	}

	public QOException(final int httpStatus, final int code, final String msg, final Throwable cause) {
		this(httpStatus, code, msg, null, cause);
	}

	public QOException(final int code, final String msg, final Map<String, Object> extraData) {
		this(code, msg, extraData, null);
	}

	public QOException(final int code, final String msg, final Map<String, Object> extraData, final Throwable cause) {
		this(DEFAULT_APP_ERROR_STATUS_CODE, code, msg, extraData, cause);
	}

	public QOException(final int httpStatus, final int code, final String msg, final Map<String, Object> extraData) {
		this(httpStatus, code, msg, extraData, null);
	}

	public QOException(final int httpStatus, final int code, final String msg, final Map<String, Object> extraData,
			final Throwable cause) {
		super(msg, cause);
		this.httpStatus = httpStatus;
		this.code = code;
		if (extraData != null) {
			this.errorData.putAll(extraData);
		}
	}

	public int getHttpStatus() {
		return httpStatus;
	}
	
	public int getCode() {
		return code;
	}

	public Map<String, Object> getErrorData() {
		return errorData;
	}
	
	public static class ErrorParams extends HashMap<String, Object> implements Serializable {

		private static final long serialVersionUID = 1193043084819909738L;
		
	}

	public static class ErrorCodes {
		
		private ErrorCodes() {
			super();
		}
		public static final int UNKNOWN = 9999;

		public static final int ACCESS_DENIED = 1001;
		public static final int INVALID_CREDENTIALS = 1002;
		public static final int INVALID_TOKEN = 1003;
		
		public static final int DESCRIPTOR_UPDATE_ERROR = 2001;
		public static final int ELEMENT_NOT_FOUND = 2004;
		
		public static final int MISSING_REQUIRED_DATA = 3001;
		public static final int INVALID_DATA = 3002;
		
		public static final int MAX_USERS_DEVICES = 4001;
		public static final int DOCUMENT_ERROR = 4002;

		
	}
}
