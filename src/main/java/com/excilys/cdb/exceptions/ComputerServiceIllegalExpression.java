package com.excilys.cdb.exceptions;

public class ComputerServiceIllegalExpression  extends Exception {

  /**
  *
  */
 private static final long serialVersionUID = 1L;

 /**
  * Computer name have some illegal expressions.
  *
  * @param message
  *          asName
  */
 public ComputerServiceIllegalExpression(String message) {
   super(message + " Some illegal expressions are detected in name");
 }
}
