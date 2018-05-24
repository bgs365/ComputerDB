package com.excilys.cdb.dto;

import java.time.LocalDate;

import com.excilys.cdb.model.Computer;

public class ComputerDTO {
	private int id;
	private String name;
  private LocalDate introduced;
  private LocalDate discontinued;
  private CompanyDTO company;
  
  public ComputerDTO(Computer computer) {
  	this.id = computer.getId();
  	this.name = computer.getName();
  	this.introduced = computer.getIntroduced();
  	this.discontinued = computer.getDiscontinued();
  	this.company = new CompanyDTO(computer.getCompany());
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

	public LocalDate getIntroduced() {
		return introduced;
	}

	public void setIntroduced(LocalDate introduced) {
		this.introduced = introduced;
	}

	public LocalDate getDiscontinued() {
		return discontinued;
	}

	public void setDiscontinued(LocalDate discontinued) {
		this.discontinued = discontinued;
	}

	public CompanyDTO getCompany() {
		return company;
	}

	public void setCompany(CompanyDTO company) {
		this.company = company;
	}
}
