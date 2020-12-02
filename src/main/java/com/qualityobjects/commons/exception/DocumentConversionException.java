package com.qualityobjects.commons.exception;

import org.springframework.http.HttpStatus;

/**
 * This exception is thrown when an error is raised in a conversion document process
 *
 * @author Siroco Team [siroco@qualityobjects.com]
 * @since 1.2.0
 */
public class DocumentConversionException extends QOException {

  private static final long serialVersionUID = -1773642493200622300L;

  public DocumentConversionException() {
    super(HttpStatus.EXPECTATION_FAILED.value(), ErrorCodes.DOCUMENT_ERROR, "Error converting document format");
  }

}
