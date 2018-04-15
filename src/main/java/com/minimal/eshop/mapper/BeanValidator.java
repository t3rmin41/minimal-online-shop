package com.minimal.eshop.mapper;

import java.io.Serializable;
import java.util.List;
import com.minimal.eshop.errorhandling.ErrorField;
import com.minimal.eshop.errorhandling.WrongBeanFormatException;

public interface BeanValidator {

  List<ErrorField> validateBean(Serializable bean) throws WrongBeanFormatException;
  
}
