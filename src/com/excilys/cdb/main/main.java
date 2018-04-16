package com.excilys.cdb.main;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.excilys.cdb.dao.CompanyDAO;
import com.excilys.cdb.dao.ComputerDAO;
import com.excilys.cdb.model.Company;
import com.excilys.cdb.model.Computer;

public class main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//testModel();
		//TestCompanyDAO();
		Computer computer = new ComputerDAO().findById(1);
		System.out.println(computer);
		
		List<Computer> computers = new ArrayList<Computer>();
		computers = new  ComputerDAO().findAll();
		System.out.println(computers);
		
		List<Computer> computer2 = new ArrayList<Computer>();
		computer2 = new  ComputerDAO().findByName(computer);
		
		System.out.println(computer2);
		
	}
	
	
	
	public static void testModel() {
		Company capcom = new Company(1, "Cap Com");
		Computer computer1 = new Computer(0, "dell 1",LocalDate.of(2017,3,29), LocalDate.of(2017,3,29));
		Computer computer2 = new Computer(0, "dell 1",LocalDate.of(2017,3,29), null);
		System.out.println(computer1);
		System.out.println(computer2);
		
		List<Computer> ordiCapcom = new ArrayList<Computer>();
		ordiCapcom.add(computer1);
		ordiCapcom.add(computer2);
		capcom.setComputer(ordiCapcom);
		capcom.addComputer(computer1);
		capcom.addComputer(computer2);
			
		System.out.println(capcom);
	}
	
	public static void TestCompanyDAO() {
		//find by id
		Company company = new CompanyDAO().findById(1);
		System.out.println(company);
		
		//find by all
		List<Company> companies = new ArrayList<Company>();
		companies = new  CompanyDAO().findAll();
		
		System.out.println(companies);
		
		//find by name
		List<Company> companies2 = new ArrayList<Company>();
		companies2 = new  CompanyDAO().findByName(company);
		
		System.out.println(companies2);
	}

}
