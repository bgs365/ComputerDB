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
	static ComputerService cs = ComputerService.INSTANCE;
	public static void main(String[] args) {
		//MenuPricipal.menuPrincipal();
		verifServiceComputer();
		//verifServiceComputerSave();
		//verifServiceCompany();
		//verifServiceComputerUpdate();
		//verifServiceComputerDelete();
	}
	
	/*Verification des services*/
	public static void verifServiceComputerUpdate() {
		Computer computer= ComputerDAO.INSTANCE.findById(577);
		computer.setName("Test apres");
		Company company = CompanyDAO.INSTANCE.findById(17);
		computer.setDiscontinued(LocalDate.of(2018,3,17));
		computer.setIntroduced(LocalDate.of(2019,3,17));
		computer.setCompany(company);
		cs.update(computer);
	}
	
	public static void verifServiceComputerDelete() {
		Computer computer = ComputerDAO.INSTANCE.findById(591);
		cs.delete(computer);
	}
	
	public static void verifServiceComputerSave() {
		Company company = CompanyDAO.INSTANCE.findById(5);
		Computer computer1 = new Computer(0, "dell 1004 verif service4 sans company sans dateDisc.. ",null, null);
		computer1.setCompany(company);
		cs.save(computer1);
	}
	
	public static void verifServiceComputer() {
		List<Computer> computers = new ArrayList<Computer>();
		Computer computer = cs.findById(200);
		System.out.println(computer);
		
		computers = cs.findAll();
		for(Computer cpt : computers) {
			System.out.println("<-- "+cpt.getName()+" --> introduced = "+cpt.getIntroduced()+" | discontinued = "+cpt.getDiscontinued()+" | "+cpt.getCompany().getName()+" -->");
		}
		
		computers = cs.findByName("dell 1000");
		for(Computer cpt : computers) {
			System.out.println("<-- "+cpt.getName()+" --> introduced = "+cpt.getIntroduced()+" | discontinued = "+cpt.getDiscontinued()+" | "+cpt.getCompany().getName()+" -->");
		}
		
		computers = null;
		computers = cs.findByCompany(1);
		for(Computer cpt : computers) {
			System.out.println("<-- "+cpt.getName()+" --> introduced = "+cpt.getIntroduced()+" | discontinued = "+cpt.getDiscontinued()+" | "+cpt.getCompany().getName()+" -->");
		}
		computers = null;
		computers = cs.findLimitNumberOfResult(0, 10);
		for(Computer cpt : computers) {
			System.out.println("<-- "+cpt.getName()+" --> introduced = "+cpt.getIntroduced()+" | discontinued = "+cpt.getDiscontinued()+" | "+cpt.getCompany().getName()+" -->");
		}
		
	}
	
	public static void verifServiceCompany() {
		CompanyService cs = CompanyService.INSTANCE;
		Company company = cs.findById(1);
		System.out.println(company);
		
		List<Company> companies = new ArrayList<Company>();
		companies = cs.findAll();
		System.out.println(companies);
		
		company = null;
		company = cs.findbyName("Samsung Electronics");
		System.out.println(company);
		
		companies = null;
		companies = cs.findLimitNumberOfResult(0, 10);
		System.out.println(companies);
		
	}


}
