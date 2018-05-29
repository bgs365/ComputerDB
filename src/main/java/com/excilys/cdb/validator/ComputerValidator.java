package com.excilys.cdb.validator;

import java.time.LocalDate;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.excilys.cdb.exceptions.ComputerServiceDateException;
import com.excilys.cdb.exceptions.ComputerServiceIllegalExpression;
import com.excilys.cdb.exceptions.ComputerServiceNameTooShortException;
import com.excilys.cdb.main.Main;

public class ComputerValidator {
	
  static final Logger LOGGER = LoggerFactory.getLogger(Main.class);

  /**
   *
   * @param name
   *          asName
   * @return (true or false)
   * @throws ComputerServiceNameTooShortException
   *           asName
   */
  public boolean verifComputerNameBeforeSave(String name) throws ComputerServiceNameTooShortException {
    boolean validComputer = false;
    if ((name.length() < 5 || StringUtils.isBlank(name))) {
      throw new ComputerServiceNameTooShortException(name);
    } else {
      validComputer = true;
    }
    return validComputer;
  }

  /**
   *
   * @param name
   *          compuerName
   * @return (true or false)
   * @throws ComputerServiceIllegalExpression
   *           asName
   */
  public boolean verifPresenceOfIllegalExpressionBeforeSave(String name) throws ComputerServiceIllegalExpression {
    boolean validComputer = false;
    if (name.contains("<") || name.contains(">") || name.contains("//") || name.contains("\\") || name.contains("/*")
        || name.contains("*/")) {
      throw new ComputerServiceIllegalExpression(name);
    } else {
      validComputer = true;
    }
    return validComputer;
  }

  /**
   *
   * @param introduced
   *          asName
   * @param discounted
   *          asName
   * @return (true or false)
   * @throws ComputerServiceDateException
   *           asName
   */
  public boolean verifDate(LocalDate introduced, LocalDate discounted) throws ComputerServiceDateException {
    boolean validDate = false;
    if (introduced == null && discounted == null) {
      validDate = true;
    } else if (introduced != null & discounted != null) {
      if (introduced.isBefore(discounted)) {
        validDate = true;
      } else {
        throw new ComputerServiceDateException();
      }
    } else if (introduced != null && discounted == null) {
      validDate = true;
    }else {
    	throw new ComputerServiceDateException();
    }
    return validDate;
  }

}
