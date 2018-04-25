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
	 * @param id
	 * @param name
	 * @param computer
	 */
	public Company(int id, String name, List<Computer> computer) {
		this.id = id;
		this.name = name;
		this.computers = computer;
	}

	/**
	 * Constructor which can take a list of computer.
	 *
	 * @param id
	 * @param name
	 */
	public Company(int id, String name) {
		this.id = id;
		this.name = name;
	}

	/**
	 * id getter.
	 *
	 * @return id.
	 */
	public int getId() {
		return id;
	}

	/**
	 * id setter.
	 *
	 * @param id
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * name getter.
	 *
	 * @return name
	 */
	public String getName() {
		return name;
	}

	/**
	 * name setter.
	 *
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * company computers getter.
	 *
	 * @return List<Computer>
	 */
	public List<Computer> getComputer() {
		return computers;
	}

	/**
	 * company computers setter.
	 *
	 * @param pComputers
	 */
	public void setComputer(List<Computer> pComputers) {
		this.computers = pComputers;
	}

	/**
	 * add an computer to company.
	 *
	 * @param computer
	 */
	public void addComputer(Computer computer){
		computers.add(computer);
	}

	@Override
	public String toString() {
		return "[" + id + " -- " + name + "]";
	}

}