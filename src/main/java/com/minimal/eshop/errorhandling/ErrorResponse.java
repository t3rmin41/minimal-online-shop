package com.minimal.eshop.errorhandling;

import java.util.ArrayList;
import java.util.List;

public class ErrorResponse {

  private String errorMessage;
  private final List<ErrorField> errors = new ArrayList<ErrorField>();
  
  public ErrorResponse(String message, List<ErrorField> errors) {
    this.errorMessage = message;
    this.errors.addAll(errors);
  }
  
  public String getErrorMessage() {
    return errorMessage;
  }
  public ErrorResponse setErrorMessage(String errorMessage) {
    this.errorMessage = errorMessage;
    return this;
  }
  public List<ErrorField> getErrors() {
    return errors;
  }
  
}
