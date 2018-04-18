package com.excilys.cdb.main;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.excilys.cdb.dao.CompanyDAO;
import com.excilys.cdb.dao.ComputerDAO;
import com.excilys.cdb.model.Company;
import com.excilys.cdb.model.Computer;
import com.excilys.cdb.service.CompanyService;
import com.excilys.cdb.service.ComputerService;
import com.excilys.cdb.ui.MenuPricipal;

public class main {

	public static void main(String[] args) {
		//MenuPricipal.menuPrincipal();
		//verifModel() ;
		//verifServiceComputer();
		//verifComputerDAOSelect();
		//verifServiceComputerSave();
	}
	
	/*Verification des services*/
	public static void verifServiceComputerUpdate() {
		ComputerService cs = new ComputerService();
		Computer computer= new ComputerDAO().findById(579);
		computer.setDiscontinued(LocalDate.of(2018,3,17));
		computer.setIntroduced(LocalDate.of(2019,3,17));
		cs.updateInfos(computer);
		
		Company company = new CompanyDAO().findById(1);
		Computer computer2= new ComputerDAO().findById(577);
		cs.updateCompany(computer2, company);
	}
	
	public static void verifServiceComputerDelete() {
		ComputerService cs = new ComputerService();
		Computer computer = new ComputerDAO().findById(591);
		cs.delete(computer);
	}
	
	public static void verifServiceComputerSave() {
		ComputerService cs = new ComputerService();
		Company company = new CompanyDAO().findById(5);
		Computer computer1 = new Computer(0, "dell 1004 verif service3 sans company sans dateDisc.. ",null, null);
		computer1.setCompany(company);
		cs.save(computer1);
	}
	
	public static void verifServiceComputer() {
		ComputerService cs = new ComputerService();
		Computer computer = cs.findById(200);
		System.out.println(computer);
		
		List<Computer> computers = new ArrayList<Computer>();
		computers = cs.findAll();
		System.out.println(computers);
		
		computers = null;
		computers = cs.findByName("dell 1000");
		System.out.println(computers);
		
		computers = null;
		computers = cs.findByCompany(25);
		System.out.println(computers);
		
		
		
	}
	
	public static void verifServiceCompany() {
		CompanyService cs = new CompanyService();
		Company company = cs.findById(1);
		System.out.println(company);
		List<Company> companies = new ArrayList<Company>();
		companies = cs.findAll();
		System.out.println(companies);
		company = null;
		company = cs.findbyName("Samsung Electronics");
		System.out.println(company);
	}
	
	
	/*
	 * Les méthodes suivantes contiennent les differents cas d'utilisations des classes créees 
	 */
	
	public static void verifModel() {
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
	
	public static void verifCompanyDAOSelect() {
		//find by id
		Company company = new CompanyDAO().findById(1);
		System.out.println(company);
		
		//find by all
		List<Company> companies = new ArrayList<Company>();
		companies = new  CompanyDAO().findAll();
		
		System.out.println(companies);
		
		//find by name
		Company companies2 = new Company();
		companies2 = new  CompanyDAO().findByName(company);
		
		System.out.println(companies2);
		
		
		
	}
	
	public static void verifComputerDAOSelect() {
		Computer computer = new ComputerDAO().findById(7);
		System.out.println(computer.getCompany().getName());
		
		List<Computer> computers = new ArrayList<Computer>();
		computers = new  ComputerDAO().findAll();
		System.out.println(computers);
		
		List<Computer> computer2 = new ArrayList<Computer>();
		computer2 = new  ComputerDAO().findByName(computer);
		
		System.out.println(computer2);
		
		//find by company
		List<Computer> computer3 = new ArrayList<Computer>();
		Company company = new CompanyDAO().findById(1);
		computer3 = new  ComputerDAO().findByCompany(company);
		
		System.out.println(computer3);
	}
	
	public static void verifSaveComputer() {
		Company company = new CompanyDAO().findById(2);
		//Company company = new Company(45, "Cap Com");
		Computer computer1 = new Computer(0, "dell 1004",LocalDate.of(2018,3,29), LocalDate.of(2019,3,29));
		computer1.setCompany(company);
		ComputerDAO saveComputer = new ComputerDAO();
		saveComputer.save(computer1);
		
	}
	
	public static void verifComputerDAODelete() {
		Computer computer = new ComputerDAO().findById(585);
		ComputerDAO sup = new ComputerDAO();
		sup.delete(computer);
	}
	
	public static void verifComputerDAOUpdate() {
		Computer computer= new ComputerDAO().findById(579);
		computer.setDiscontinued(LocalDate.of(2018,4,17));
		computer.setIntroduced(LocalDate.of(2019,4,17));
		ComputerDAO upd = new ComputerDAO();
		upd.update(computer);
		
		Company company = new CompanyDAO().findById(6);
		Computer computer2= new ComputerDAO().findById(577);
		upd.updateCompany(computer2, company);
	}

}
