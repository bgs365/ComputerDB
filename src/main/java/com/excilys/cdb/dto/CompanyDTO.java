package com.excilys.cdb.dto;

import com.excilys.cdb.model.Company;

public class CompanyDTO {
	private int id;
  private String name;

	/**
   * Default Constructor.
   */
  public CompanyDTO(Company company) {
  	this.id = company.getId();
  	this.name = company.getName();
  }
  

  public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	
}
