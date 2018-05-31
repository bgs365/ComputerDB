package com.excilys.cdb.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;



/**
 * Class Company.
 *
 * @author sanogo
 *
 */
@Entity
@Table(name = "company")
public class Company implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;
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
  public Company(Long id, String name) {
    this.id = id;
    this.name = name;
  }


  public Long getId() {
    return id;
  }

  public void setId(Long id) {
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