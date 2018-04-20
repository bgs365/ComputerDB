package com.excilys.cdb.model;

import java.util.ArrayList;
import java.util.List;

public class Company {
	
	private int id;
	private String name;
	private List<Computer> computers = new ArrayList<Computer>();
	
	public Company() {
	}
	
	public Company(int id, String name, List<Computer> computer) {
		this.id = id;
		this.name = name;
		this.computers = computer;
	}
	
	
	
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
	
	public void addComputer(Computer computer) {
		computers.add(computer);
	}

	@Override
	public String toString() {
		return "[" + id +" -- "+ name +  "]";
	}
	
	
	
}
