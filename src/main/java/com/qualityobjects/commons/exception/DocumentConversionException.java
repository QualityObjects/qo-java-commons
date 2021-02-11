package com.qualityobjects.commons.exception;

/**
 * This exception is thrown when an error is raised in a conversion document process
 *
 * @author Siroco Team [siroco@qualityobjects.com]
 * @since 1.2.0
 */
public class DocumentConversionException extends QOException {

  private static final long serialVersionUID = -1773642493200622300L;

  public DocumentConversionException() {
    super(417 /*EXPECTATION_FAILED*/, ErrorCodes.DOCUMENT_ERROR, "Error converting document format");
  }

}
