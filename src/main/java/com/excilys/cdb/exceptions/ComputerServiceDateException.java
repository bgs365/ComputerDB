package com.excilys.cdb.exceptions;

public class ComputerServiceDateException extends Exception {

  /**
  *
  */
  private static final long serialVersionUID = 1L;

  /**
   * dates have some problems.
   */
  public ComputerServiceDateException() {
    super(" Problems with dates detected");
  }

}
