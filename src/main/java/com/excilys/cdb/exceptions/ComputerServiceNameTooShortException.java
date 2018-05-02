package com.excilys.cdb.exceptions;

/**
 * Exception for computer name size.
 *
 * @author sanogo
 *
 */
public class ComputerServiceNameTooShortException extends Exception {

  /**
   *
   */
  private static final long serialVersionUID = 1L;

  /**
   * Computer name too short.
   *
   * @param message
   *          asName
   */
  public ComputerServiceNameTooShortException(String message) {
    super(message + " is not a correct value, computer name must be at the less 5 carracters");
  }

}
