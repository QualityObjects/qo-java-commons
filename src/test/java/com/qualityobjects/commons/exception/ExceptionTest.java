package com.qualityobjects.commons.exception;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Map;

import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

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
	
	@Test
	void descriptorUpdateErrorExceptionTest() {
		QOException e = new DescriptorUpdateErrorException("Descriptor Update Error");
		assertNotNull(e);
	}
	
	@Test
	void documentConversionException( ) {
		QOException e = new DocumentConversionException();
		assertNotNull(e);
	}
	
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
	
	@Test
	void invalidActivityTimeRangeExceptionTest() {
		QOException e = new InvalidActivityTimeRangeException();
		assertNotNull(e);
	}
	
	@Test
	void invalidBbddDataExceptionTest() {
		QOException e = new InvalidBBDDDataException("Invalid Data Error");
		assertNotNull(e);
	}
	
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
	
	@Test
	void maxUserDevicesReachedExceptionTest() {
		QOException e = new MaxUserDevicesReachedException(5);
		assertNotNull(e);
	}
	
	@Test
	void notSupportedDocFormatException() {
		QOException e = new NotSupportedDocFormat();
		assertNotNull(e);
	}
	
	@Test
	void qoExceptionTest() {
		QOException e = new QOException();
		String message = "Internal Server Error";
		int errorCode = ErrorCodes.UNKNOWN;
		e = new QOException(errorCode, message);
		assertNotNull(e);
		
		e = new QOException(errorCode, message, new InternalServerErrorException(message));
		assertNotNull(e);
		
		int httpStatus = HttpStatus.INTERNAL_SERVER_ERROR.value();
		e = new QOException(httpStatus, errorCode, message, new InternalServerErrorException(message));
		assertNotNull(e);
	}
	
	@Test
	void qoRuntimeExceptionTest() {
		QORuntimeException re = new QORuntimeException(ErrorCodes.ACCESS_DENIED, "Access Denied");
		int errorCode = re.getHttpStatus();
		assertNotNull(re);
	}
	
	@Test
	void sqlSetExceptionTest() {
		QORuntimeException re = new SQLSetException();
		assertNotNull(re);
	}
}
