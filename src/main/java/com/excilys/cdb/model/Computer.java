package com.excilys.cdb.model;

import java.time.LocalDate;

/**
 *Computer Class. 
 *
 * @author sanogo
 *
 */
public class Computer {

	private int id;
	private String name;
	private LocalDate introduced;
	private LocalDate discontinued;
	private Company company;
	/**
	 * Constructeur par défault.
	 */
	public Computer() {
	}

	/**
	 * Constructeur sans company.
	 *
	 * @param id
	 * @param name
	 * @param introduced
	 * @param discontinued
	 */
	public Computer(int id, String name, LocalDate introduced, LocalDate discontinued) {
		this.id = id;
		this.name = name;
		this.introduced = introduced;
		this.discontinued = discontinued;
	}

	/**
	 *Constructeur avec company.
	 *
	 * @param id
	 * @param name
	 * @param introduced
	 * @param discontinued
	 * @param company
	 */
	public Computer(int id, String name, LocalDate introduced, LocalDate discontinued, Company company) {
		super();
		this.company = company;
	}
	
	/**
	 * name getter.
	 *
	 * @return
	 */
	public String getName(){
		return name;
	}
	
	/**
	 *name setter.
	 *
	 * @param name
	 */
	public void setName(String name){
		this.name = name;
	}

	/**
	 *introduce date getter.
	 *
	 * @return
	 */
	public LocalDate getIntroduced() {
		return introduced;
	}

	/**
	 *introduce date getter.
	 *
	 * @param introduced
	 */
	public void setIntroduced(LocalDate introduced) {
		this.introduced = introduced;
	}
	
	/**
	 *discontinued date getter.
	 *
	 * @return
	 */
	public LocalDate getDiscontinued() {
		return discontinued;
	}
	
	/**
	 *Discontinued date setter.
	 *
	 * @param discontinued
	 */
	public void setDiscontinued(LocalDate discontinued) {
		this.discontinued = discontinued;
	}
	
	/**
	 *id getter.
	 *
	 * @return
	 */
	public int getId() {
		return id;
	}
	
	/**
	 *id setter.
	 *
	 * @param id
	 */
	public void setId(int id) {
		this.id = id;
	}
	
	/**
	 *company getter.
	 * @return
	 */
	public Company getCompany() {
		return company;
	}
	
	/**
	 *Company setter.
	 *
	 * @param company
	 */
	public void setCompany(Company company) {
		this.company = company;
	}

	@Override
	public String toString() {
		return "Computer [id=" + id + ", " + (name != null ? "name=" + name + ", " : "")
				+ (introduced != null ? "introduced=" + introduced + ", " : "")
				+ (discontinued != null ? "discontinued=" + discontinued + ", " : "")
				+ (company != null ? "company=" + company : "") + "]";
	}

}