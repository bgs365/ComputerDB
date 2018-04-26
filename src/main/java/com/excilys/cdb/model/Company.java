package com.excilys.cdb.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Class Company.
 *
 * @author sanogo
 *
 */
public class Company {

  private int id;
  private String name;
  private List<Computer> computers = new ArrayList<Computer>();

  /**
   * Default Constructor.
   */
  public Company() {
  }

  /**
   * Constructor which can take his list of computers.
   *
   * @param id Company id
   * @param name Company name
   * @param computer Company List<Computers>
   */
  public Company(int id, String name, List<Computer> computer) {
    this.id = id;
    this.name = name;
    this.computers = computer;
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

  public List<Computer> getComputer() {
    return computers;
  }

  public void setComputer(List<Computer> pComputers) {
    this.computers = pComputers;
  }

  /**
   * add an computer to company.
   *
   * @param computer Computer
   */
  public void addComputer(Computer computer) {
    computers.add(computer);
  }

  @Override
  public String toString() {
    return "[" + id + " -- " + name + "]";
  }

}