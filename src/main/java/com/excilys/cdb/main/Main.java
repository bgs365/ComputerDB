package com.excilys.cdb.main;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.excilys.cdb.exceptions.CdbException;
import com.excilys.cdb.exceptions.ComputerServiceIllegalExpression;
import com.excilys.cdb.exceptions.ComputerServiceNameTooShortException;
import com.excilys.cdb.model.Company;
import com.excilys.cdb.model.Computer;
import com.excilys.cdb.service.CompanyService;
import com.excilys.cdb.service.ComputerService;
import com.excilys.cdb.springConfig.ApplicationConfig;
import com.excilys.cdb.ui.MainMenu;

/**
 * Main class, which allow to verify class.
 *
 * @author sanogo
 *
 */
public class Main {
	
	static Logger LOGGER = LoggerFactory.getLogger(Main.class);
	static ApplicationContext context = new AnnotationConfigApplicationContext(ApplicationConfig.class);
	static CompanyService companyService = (CompanyService) context.getBean(CompanyService.class);
	static ComputerService computerService = (ComputerService) context.getBean(ComputerService.class);

	/**
	 * Main method.
	 *
	 * @param args
	 *          asName
	 */
	public static void main(String[] args) {

		
		  LOGGER.info("Ouverture du Menu");
		  MainMenu.display();
		  LOGGER.info("Fermeture du Menu");
		 
		// verifServiceComputer();
		// verifServiceComputerSave();
		// verifServiceCompany();
		// verifServiceComputerUpdate();
		// verifServiceComputerDelete();
		// verifDAODeleteCompany();

	}

	/**
	 * test update method of computer Service.
	 *
	 * @throws ComputerServiceIllegalExpression
	 * @throws ComputerServiceNameTooShortException
	 */
	public void verifServiceComputerUpdate() {
		Computer computer = computerService.findById(614);
		computer.setName("Test update sans company");
		// Company company = companyService.findById(17);
		computer.setIntroduced(LocalDate.of(2018, 3, 18));
		computer.setDiscontinued(null);
		computer.setCompany(new Company(0, null));
		try {
			System.out.println(computerService.update(computer));

		} catch (CdbException e) {
			LOGGER.info(e.getMessage());
		}
	}

	/**
	 * test delete method of Computer Service.
	 */
	public void verifServiceComputerDelete() {
		Computer computer = computerService.findById(583);
		System.out.println(computerService.delete(computer.getId()));
	}

	/**
	 * test save method of Computer service.
	 */
	public void verifServiceComputerSave() {
		Company company = companyService.findById(5);
		Computer computer1 = new Computer(0, "dell 1004 verif 3 service avec company sans dateDisc.. ", null, null);
		computer1.setCompany(company);
		try {
			System.out.println(computerService.save(computer1));
		} catch (CdbException e) {
			LOGGER.info(e.getMessage());
		}
	}

	/**
	 * test select methods of Computer service.
	 */
	public void verifServiceComputer() {
		List<Computer> computers = new ArrayList<Computer>();
		Computer computer = computerService.findById(1000);
		System.out.println(computer);

		computers = computerService.findAll();
		for (Computer cpt : computers) {
			System.out.println("<-- " + cpt.getName() + " --> introduced = " + cpt.getIntroduced() + " | discontinued = "
			    + cpt.getDiscontinued() + " | " + cpt.getCompany().getName() + " -->");
		}

		computers = computerService.findByName("iPhone 4S");
		for (Computer cpt : computers) {
			System.out.println("<-- " + cpt.getName() + " --> introduced = " + cpt.getIntroduced() + " | discontinued = "
			    + cpt.getDiscontinued() + " | " + cpt.getCompany().getName() + " -->");
		}

		computers = null;
		computers = computerService.findByCompany(1);
		for (Computer cpt : computers) {
			System.out.println("<-- " + cpt.getName() + " --> introduced = " + cpt.getIntroduced() + " | discontinued = "
			    + cpt.getDiscontinued() + " | " + cpt.getCompany().getName() + " -->");
		}
		computers = null;
		computers = computerService.findLimitNumberOfResult(0, 10);
		for (Computer cpt : computers) {
			System.out.println("<-- " + cpt.getName() + " --> introduced = " + cpt.getIntroduced() + " | discontinued = "
			    + cpt.getDiscontinued() + " | " + cpt.getCompany().getName() + " -->");
		}

		computers = computerService.findByComputerAndCompanyNameLimit("Apple", 40, 10);
		for (Computer cpt : computers) {
			System.out.println("<-- " + cpt.getName() + " --> introduced = " + cpt.getIntroduced() + " | discontinued = "
			    + cpt.getDiscontinued() + " | " + cpt.getCompany().getName() + " -->");
		}

	}

	/**
	 * test selects methods of company.
	 */
	public static void verifServiceCompany() {
		Company company = companyService.findById(500);
		System.out.println(company);

		List<Company> companies = new ArrayList<Company>();
		companies = companyService.findAll();
		System.out.println(companies);

		companies = null;
		companies = companyService.findbyName("Samsung");
		System.out.println(companies);

		companies = null;
		companies = companyService.findLimitNumberOfResult(0, 10);
		System.out.println(companies);

	}

}
