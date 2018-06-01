package com.excilys.cdb.model;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Computer Class.
 *
 * @author sanogo
 *
 */
@Entity
@Table(name = "computer")
public class Computer implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	private String name;
	@Convert(converter=serialiseComputer.class)
	private LocalDate introduced;
	@Convert(converter=serialiseComputer.class)
	private LocalDate discontinued;
	@ManyToOne
	private Company company;

	/**
	 * Constructeur par défault.
	 */
	public Computer() {
	}

	/**
	 * Constructeur sans company.
	 *
	 * @param id asName
	 * @param name asName
	 * @param introduced asName
	 * @param discontinued asName
	 */
	public Computer(long id, String name, LocalDate introduced, LocalDate discontinued) {
		this.id = id;
		this.name = name;
		this.introduced = introduced;
		this.discontinued = discontinued;
	}

	/**
	 * Constructeur avec company.
	 *
	 * @param id asName
	 * @param name asName
	 * @param introduced date
	 * @param discontinued date
	 * @param company asName
	 */
	public Computer(long id, String name, LocalDate introduced, LocalDate discontinued, Company company) {
		this.id = id;
		this.name = name;
		this.introduced = introduced;
		this.discontinued = discontinued;
		this.company = company;
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

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Company getCompany() {
		return company;
	}

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