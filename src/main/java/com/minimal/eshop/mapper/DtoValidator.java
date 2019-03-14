package com.minimal.eshop.mapper;

import java.io.Serializable;
import java.util.List;
import com.minimal.eshop.errorhandling.ErrorField;
import com.minimal.eshop.errorhandling.WrongDtoFormatException;

public interface DtoValidator {

  List<ErrorField> validatedto(Serializable dto) throws WrongDtoFormatException;
  
}
