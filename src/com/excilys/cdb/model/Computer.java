package com.excilys.cdb.model;

import java.time.LocalDate;

public class Computer {
	
	private int id;
	private String name;
	private LocalDate introduced;               
	private LocalDate discontinued ;  
  
	public Computer(int id, String name, LocalDate introduced, LocalDate discontinued) {
		this.id = id;
		this.name = name;
		this.introduced = introduced;
		this.discontinued = discontinued;
	}



	public Computer() {
		// TODO Auto-generated constructor stub
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

	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}



	@Override
	public String toString() {
		return "Computer [id=" + id + ", name=" + name + ", introduced=" + introduced + ", discontinued=" + discontinued + "] \n";
	}

	
	
}
