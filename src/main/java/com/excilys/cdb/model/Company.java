package com.excilys.cdb.model;

/**
 * Class Company.
 *
 * @author sanogo
 *
 */
public class Company {

  private int id;
  private String name;

  /**
   * Default Constructor.
   */
  public Company() {
  }


  /**
   * Constructor which can take a list of computer.
   *
   * @param id asPreviews
   * @param name asPreviews
   */
  public Company(int id, String name) {
    this.id = id;
    this.name = name;
  }


  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }


  @Override
  public String toString() {
    return "[" + id + " -- " + name + "]";
  }

}