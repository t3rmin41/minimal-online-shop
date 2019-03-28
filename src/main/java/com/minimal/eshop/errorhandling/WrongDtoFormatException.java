package com.minimal.eshop.errorhandling;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("serial")
public class WrongDtoFormatException extends RuntimeException {

  private final List<ErrorField> errors = new ArrayList<ErrorField>();

  public WrongDtoFormatException(String message, List<ErrorField> errors) {
    super(message);
    this.errors.addAll(errors);
  }
  
  public WrongDtoFormatException(String message, Throwable cause, List<ErrorField> errors) {
    super(message, cause);
    this.errors.addAll(errors);
  }
  
  public WrongDtoFormatException(Throwable cause, List<ErrorField> errors) {
    super(cause);
    this.errors.addAll(errors);
  }
  
  public WrongDtoFormatException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace, List<ErrorField> errors) {
    super(message, cause, enableSuppression, writableStackTrace);
    this.errors.addAll(errors);
  }
  
  public List<ErrorField> getErrors() {
    return errors;
  }

}