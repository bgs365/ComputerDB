package com.excilys.cdb.dto;

import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;

import com.excilys.cdb.model.Computer;

public class ComputerDTO {
	private int id;
	private String introduced;
	private String discontinued;
	private int companyId;
	@NotBlank
  @Size(min=5, max=30) 
	private String name;

	public ComputerDTO(Computer computer) {
		this.id = computer.getId();
		this.name = computer.getName();
		this.introduced = computer.getIntroduced().toString();
		this.discontinued = computer.getDiscontinued().toString();
		this.companyId = computer.getCompany().getId();
	}

	public ComputerDTO() {
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

	public String getIntroduced() {
		return introduced;
	}

	public void setIntroduced(String introduced) {
		this.introduced = introduced;
	}

	public String getDiscontinued() {
		return discontinued;
	}

	public void setDiscontinued(String discontinued) {
		this.discontinued = discontinued;
	}

	public int getCompanyId() {
		return companyId;
	}

	public void setCompanyId(int companyId) {
		this.companyId = companyId;
	}

}
