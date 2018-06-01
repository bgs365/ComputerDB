package com.excilys.cdb.exceptions;

public class ComputerServiceDateException extends CdbException {

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
