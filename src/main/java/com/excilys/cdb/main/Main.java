package com.excilys.cdb.main;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.excilys.cdb.dao.CompanyDAO;
import com.excilys.cdb.dao.ComputerDAO;
import com.excilys.cdb.model.Company;
import com.excilys.cdb.model.Computer;
import com.excilys.cdb.service.CompanyService;
import com.excilys.cdb.service.ComputerService;

public class Main {
	static ComputerService computerService = ComputerService.INSTANCE;
	final static Logger logger = LoggerFactory.getLogger(Main.class);

	public static void main(String[] args) {
		/*
		 * logger.info("Ouverture du Menu"); MainMenu.display();
		 * logger.info("Fermeture du Menu");
		 */
		// verifServiceComputer();
		// verifServiceComputerSave();
		// verifServiceCompany();
		// verifServiceComputerUpdate();
		// verifServiceComputerDelete();
		System.out.println(CompanyDAO.INSTANCE.findById(50));
	}

	/* Verification des services */
	public static void verifServiceComputerUpdate() {
		Computer computer = ComputerDAO.INSTANCE.findById(582);
		computer.setName("Test apres");
		Company company = CompanyDAO.INSTANCE.findById(17);
		computer.setDiscontinued(LocalDate.of(2018, 3, 18));
		computer.setIntroduced(null);
		computer.setCompany(company);
		System.out.println(computerService.update(computer));
	}

	public static void verifServiceComputerDelete() {
		Computer computer = ComputerDAO.INSTANCE.findById(583);
		System.out.println(computerService.delete(575));
	}

	public static void verifServiceComputerSave() {
		Company company = CompanyDAO.INSTANCE.findById(5);
		Computer computer1 = new Computer(0, "dell 1004 verif service avec company sans dateDisc.. ", null, null);
		computer1.setCompany(company);
		System.out.println(computerService.save(computer1));
	}

	public static void verifServiceComputer() {
		List<Computer> computers = new ArrayList<Computer>();
		Computer computer = computerService.findById(200);
		System.out.println(computer);

		computers = computerService.findAll();
		for (Computer cpt : computers) {
			System.out.println("<-- " + cpt.getName() + " --> introduced = " + cpt.getIntroduced()
					+ " | discontinued = " + cpt.getDiscontinued() + " | " + cpt.getCompany().getName() + " -->");
		}

		computers = computerService.findByName("iPhone 4S");
		for (Computer cpt : computers) {
			System.out.println("<-- " + cpt.getName() + " --> introduced = " + cpt.getIntroduced()
					+ " | discontinued = " + cpt.getDiscontinued() + " | " + cpt.getCompany().getName() + " -->");
		}

		computers = null;
		computers = computerService.findByCompany(1);
		for (Computer cpt : computers) {
			System.out.println("<-- " + cpt.getName() + " --> introduced = " + cpt.getIntroduced()
					+ " | discontinued = " + cpt.getDiscontinued() + " | " + cpt.getCompany().getName() + " -->");
		}
		computers = null;
		computers = computerService.findLimitNumberOfResult(0, 10);
		for (Computer cpt : computers) {
			System.out.println("<-- " + cpt.getName() + " --> introduced = " + cpt.getIntroduced()
					+ " | discontinued = " + cpt.getDiscontinued() + " | " + cpt.getCompany().getName() + " -->");
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
