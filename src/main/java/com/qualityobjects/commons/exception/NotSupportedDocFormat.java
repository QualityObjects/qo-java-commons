package com.qualityobjects.commons.exception;


import org.springframework.http.HttpStatus;

/**
 * This exception is thrown when an error is raised in a conversion document process
 *
 * @author Siroco Team [siroco@qualityobjects.com]
 * @since 1.2.0
 */
public class NotSupportedDocFormat extends QOException {

  private static final long serialVersionUID = -1773642493200622300L;

  public NotSupportedDocFormat() {
    super(HttpStatus.UNSUPPORTED_MEDIA_TYPE.value(), ErrorCodes.DOCUMENT_ERROR, "Document format not supported");
  }
}
