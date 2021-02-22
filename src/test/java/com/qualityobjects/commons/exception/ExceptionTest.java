package com.qualityobjects.commons.exception;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Map;

import org.junit.jupiter.api.Test;

import com.qualityobjects.commons.exception.QOException.ErrorCodes;

class ExceptionTest {
	
	@Test
	void accessDeniedExceptionTest() {
		QOException e = new AccessDeniedException();
		assertNotNull(e);
	}

	@Test
	void classNotInstantiatedExceptionTest() {
		QORuntimeException re = new ClassNotInstantiatedException();
		assertNotNull(re);
	}
	
//	@Test
//	void descriptorUpdateErrorExceptionTest() {
//		QOException e = new DescriptorUpdateErrorException("Descriptor Update Error");
//		assertNotNull(e);
//	}
	
//	@Test
//	void documentConversionException( ) {
//		QOException e = new DocumentConversionException();
//		assertNotNull(e);
//	}
	
	@Test
	void elementNotFoundExceptionTest() {
		String message = "Element not found";
		QOException e = new ElementNotFoundException(message);
		e = new ElementNotFoundException("Element 1", 1);
		assertNotNull(e);
		
		Map<String, Object> errorData = e.getErrorData();
		int httpStatus = e.getHttpStatus();
		int code = e.getCode();
		e = new QOException(httpStatus, code, message, errorData);
		assertNotNull(e);
	}
	
	@Test
	void generatingCsvErrorExceptionTest() {
		QOException e = new GeneratingCSVErrorException("Error generating CSV");
		assertNotNull(e);
	}
	
	@Test
	void internalServerErrorExceptionTest() {
		QOException e = new InternalServerErrorException("Internal Server Error");
		assertNotNull(e);
	}
	
//	@Test
//	void invalidActivityTimeRangeExceptionTest() {
//		QOException e = new InvalidActivityTimeRangeException();
//		assertNotNull(e);
//	}
	
	@Test
	void invalidCredentialsExceptionTest() {
		QOException e = new InvalidCredentialsException();
		assertNotNull(e);
	}
	
	@Test
	void invalidInputDataExceptionTest() {
		QOException e = new InvalidInputDataException();
		e = new InvalidInputDataException("Invalid input data");
		assertNotNull(e);
	}
	
	@Test
	void invalidTokenExceptionTest() {
		QOException e = new InvalidTokenException();
		assertNotNull(e);
	}
	
//	@Test
//	void maxUserDevicesReachedExceptionTest() {
//		QOException e = new MaxUserDevicesReachedException(5);
//		assertNotNull(e);
//	}
	
//	@Test
//	void notSupportedDocFormatException() {
//		assertThrows(NotSupportedDocFormat.class, () -> {
//			try {
//				throw new NotSupportedDocFormat();
//			} catch (QOException ex) {
//				assertEquals("Document format not supported", ex.getMessage());
//				assertEquals(415 /* UNSUPPORTED_MEDIA_TYPE */, ex.getHttpStatus());
//				assertEquals(ErrorCodes.DOCUMENT_ERROR, ex.getCode());
//				throw ex;
//			}
//		});
//	}
	
	@Test
	void qoExceptionTest() {
		QOException e = new QOException();
		String message = "Internal Server Error";
		int errorCode = ErrorCodes.UNKNOWN;
		e = new QOException(errorCode, message);
		assertNotNull(e);
		
		e = new QOException(errorCode, message, new InternalServerErrorException(message));
		assertNotNull(e);
		
		int httpStatus = 500;
		e = new QOException(httpStatus, errorCode, message, new InternalServerErrorException(message));
		assertNotNull(e);
	}
	
	@Test
	void qoRuntimeExceptionTest() {
		assertThrows(QORuntimeException.class, () -> {
			try {
				throw new QORuntimeException("Unexpected error");
			} catch (QORuntimeException ex) {
				assertEquals("Unexpected error", ex.getMessage());
				assertEquals(QOException.DEFAULT_APP_ERROR_STATUS_CODE, ex.getHttpStatus());
				throw ex;
			}
		});

	}
		
	@Test
	void dataReadRuntimeExceptionTest() {
		assertThrows(DataReadRuntimeException.class, () -> {
			try {
				throw new DataReadRuntimeException();
			} catch (QORuntimeException ex) {
				assertEquals("Unexpected error reading data", ex.getMessage());
				assertEquals(QOException.DEFAULT_APP_ERROR_STATUS_CODE, ex.getHttpStatus());
				throw ex;
			}
		});
		
	}
}
