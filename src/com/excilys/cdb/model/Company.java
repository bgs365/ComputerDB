package com.excilys.cdb.model;

import java.util.ArrayList;
import java.util.List;

public class Company {
	
	private int id;
	private String Name;
	private List<Computer> computers = new ArrayList<Computer>();
	
	public Company(int id, String name, List<Computer> computer) {
		this.id = id;
		Name = name;
		this.computers = computer;
	}
	
	
	
	public Company(int id, String name) {
		this.id = id;
		Name = name;	
	}



	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public String getName() {
		return Name;
	}
	public void setName(String name) {
		Name = name;
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
		return "Company [id=" + id + ", Name=" + Name + ", computer=" + computers + "]";
	}
	
	
	
}
