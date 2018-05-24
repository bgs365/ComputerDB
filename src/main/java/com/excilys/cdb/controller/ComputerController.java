package com.excilys.cdb.controller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.excilys.cdb.exceptions.CdbException;
import com.excilys.cdb.model.Company;
import com.excilys.cdb.model.Computer;
import com.excilys.cdb.page.Page;
import com.excilys.cdb.service.CompanyService;
import com.excilys.cdb.service.ComputerService;

@Controller
@RequestMapping("/computer")
public class ComputerController {

	private int nombrElementPerPage = 10;
	private int numberOfComputers = 0;
	private List<Computer> computers;
	private List<Company> companies;
	private Page<Computer> computerPage;
	@Autowired
	private ComputerService computerService;
	@Autowired
	private CompanyService companyService;
	private String search = "";
	static final Logger LOGGER = LoggerFactory.getLogger(ComputerController.class);

	/**
	 * 
	 * @param model
	 * @return dashboard page
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String IndexComputer(ModelMap model) {
		setPageContent(search, 0, nombrElementPerPage);
		LOGGER.debug(" default page");
		computerPage = new Page<Computer>(computers, nombrElementPerPage, numberOfComputers);

		model.addAttribute("numberElementPerPage", nombrElementPerPage);
		model.addAttribute("numberOfComputers", numberOfComputers);
		model.addAttribute("computers", computers);
		model.addAttribute("computerPage", computerPage);
		model.addAttribute("numberTotalOfPages", totalNumberOfPages());
		model.addAttribute("computers", computers);
		model.addAttribute("numberOfComputers", numberOfComputers);
		return "dashboard";
	}

	/**
	 * 
	 * @param pageNumber
	 * @param model
	 * @return dashboard page
	 */
	@RequestMapping(value = "/searchPage", method = RequestMethod.GET)
	public String changePage(@RequestParam(value = "pageNumber") int pageNumber, ModelMap model) {

		computerPage = new Page<Computer>(computers, nombrElementPerPage, numberOfComputers);

		computerPage.setCurrentPage(pageNumber);
		setPageContent(search, computerPage.getIndexFirstPageElement(), computerPage.getNombreElementPerPage());

		model.addAttribute("search", search);
		model.addAttribute("numberElementPerPage", nombrElementPerPage);
		model.addAttribute("numberOfComputers", numberOfComputers);
		model.addAttribute("computers", computers);
		model.addAttribute("computerPage", computerPage);
		model.addAttribute("numberTotalOfPages", totalNumberOfPages());
		model.addAttribute("computers", computers);
		model.addAttribute("numberOfComputers", numberOfComputers);
		return "dashboard";
	}

	/**
	 * 
	 * @param search
	 * @param model
	 * @return dashboard page
	 */
	@RequestMapping(value = "/search", method = RequestMethod.GET)
	public String search(@RequestParam(value = "search") String pSearch, ModelMap model) {

		search = pSearch;
		computerPage = new Page<Computer>(computers, nombrElementPerPage, numberOfComputers);

		setPageContent(search, 1, computerPage.getNombreElementPerPage());

		model.addAttribute("search", search);
		model.addAttribute("numberElementPerPage", nombrElementPerPage);
		model.addAttribute("numberOfComputers", numberOfComputers);
		model.addAttribute("computers", computers);
		model.addAttribute("computerPage", computerPage);
		model.addAttribute("numberTotalOfPages", totalNumberOfPages());
		model.addAttribute("computers", computers);
		model.addAttribute("numberOfComputers", numberOfComputers);
		return "dashboard";
	}

	/**
	 * 
	 * @param numberElementDisplayed
	 * @param model
	 * @return dashboard page
	 */
	@RequestMapping(value = "/nombreElementPerPage", method = RequestMethod.GET)
	public String changeNumberOfElementDisplayPerPage(
	    @RequestParam(value = "buttonSetNumberElementDisplayed") int numberElementDisplayed, ModelMap model) {

		nombrElementPerPage = numberElementDisplayed;
		computerPage.setNombreElementPerPage(nombrElementPerPage);
		computerPage = new Page<Computer>(computers, nombrElementPerPage, numberOfComputers);

		setPageContent(search, computerPage.getIndexFirstPageElement(), computerPage.getNombreElementPerPage());

		model.addAttribute("search", search);
		model.addAttribute("numberElementPerPage", nombrElementPerPage);
		model.addAttribute("numberOfComputers", numberOfComputers);
		model.addAttribute("computers", computers);
		model.addAttribute("computerPage", computerPage);
		model.addAttribute("numberTotalOfPages", totalNumberOfPages());
		model.addAttribute("computers", computers);
		model.addAttribute("numberOfComputers", numberOfComputers);
		return "dashboard";
	}

	/**
	 * Get.
	 * 
	 * @param idComputerToModifie
	 * @param model
	 * @return editComputer page
	 */
	@RequestMapping(value = "/editComputer", method = RequestMethod.GET)
	public String editComputerGetPage(@RequestParam(value = "ComputerToModifie") int idComputerToModifie,
	    ModelMap model) {

		companies = companyService.findAll();
		if (idComputerToModifie != 0) {
			int computerId = idComputerToModifie;
			Computer computer = computerService.findById(computerId);
			model.addAttribute("computer", computer);
		}

		model.addAttribute("companies", companies);
		return "editComputer";
	}

	/**
	 * Post.
	 * 
	 * @param computerId
	 * @param name
	 * @param introduced
	 * @param discontinued
	 * @param companyId
	 * @param model
	 * @return editComputer page
	 */
	@RequestMapping(value = "/editComputer", method = RequestMethod.POST)
	public String editComputer(@RequestParam(value = "computerId") int computerId,
	    @RequestParam(value = "computerName") String name, @RequestParam(value = "introduced") String introduced,
	    @RequestParam(value = "discontinued") String discontinued, @RequestParam(value = "companyId") int companyId,
	    ModelMap model) {

		String errors = null;
		boolean success = false;
		companies = companyService.findAll();
		Computer computer = computerService.findById(computerId);
		computer.setName(name);
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

		if (!StringUtils.isBlank(introduced)) {
			computer.setIntroduced(LocalDate.parse(introduced, formatter));
		}

		if (!StringUtils.isBlank(discontinued)) {
			computer.setDiscontinued(LocalDate.parse(discontinued, formatter));
		}

		if (companyId != 0) {
			computer.setCompany(companyService.findById(companyId));
		} else {
			computer.setCompany(new Company());
		}

		try {
			LOGGER.info(computer + "");

			if (computerService.update(computer) != 0) {
				success = true;
			} else {
				success = false;
			}
		} catch (CdbException e) {
			errors += e.getMessage();
		}
		model.addAttribute("errors", errors);
		model.addAttribute("success", success);
		model.addAttribute("computer", computer);
		model.addAttribute("companies", companies);

		return "editComputer";
	}

	/**
	 * Get.
	 * 
	 * @param model
	 * @return addComputer page
	 */
	@RequestMapping(value = "/addComputer", method = RequestMethod.GET)
	public String addComputerGetPage(ModelMap model) {
		companies = companyService.findAll();
		model.addAttribute("companies", companies);
		return "addComputer";
	}

	/**
	 * 
	 * @param name
	 * @param introduced
	 * @param discontinued
	 * @param companyId
	 * @param model
	 * @return dashboard page
	 */
	@RequestMapping(value = "/addComputer", method = RequestMethod.POST)
	public String addComputer(@RequestParam(value = "computerName") String name,
	    @RequestParam(value = "introduced") String introduced, @RequestParam(value = "discontinued") String discontinued,
	    @RequestParam(value = "companyId") int companyId, ModelMap model) {

		String errors = null;
		boolean success = false;
		companies = companyService.findAll();
		Computer computer = new Computer();
		computer.setName(name);
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

		if (!StringUtils.isBlank(introduced)) {
			computer.setIntroduced(LocalDate.parse(introduced, formatter));
		}

		if (!StringUtils.isBlank(discontinued)) {
			computer.setDiscontinued(LocalDate.parse(discontinued, formatter));
		}

		if (companyId != 0) {
			computer.setCompany(companyService.findById(companyId));
		} else {
			computer.setCompany(new Company());
		}

		try {
			LOGGER.info(computer + "");

			if (computerService.save(computer) != 0) {
				success = true;
			} else {
				success = false;
			}
		} catch (CdbException e) {
			errors += e.getMessage();
		}

		model.addAttribute("errors", errors);
		model.addAttribute("success", success);
		model.addAttribute("computer", computer);
		model.addAttribute("companies", companies);
		return "addComputer";
	}

	/**
	 * 
	 * @param pageNumber
	 * @param model
	 * @return dashboard page
	 */
	@RequestMapping(value = "/deleteComputer", method = RequestMethod.POST)
	public String deleteComputer(@RequestParam(value = "selection") String idComputerSend, ModelMap model) {

		computerPage = new Page<Computer>(computers, nombrElementPerPage, numberOfComputers);

		String deleteState = "";
		boolean computerDeleteSuccess = false;
		List<String> listIdComputer = new ArrayList<String>(Arrays.asList(idComputerSend.split(",")));
		if (listIdComputer.size() > 1) {
			computerDeleteSuccess = true;
		}
		for (int i = 0; i < listIdComputer.size(); i++) {
			if (computerService.delete(Integer.parseInt(listIdComputer.get(i))) != 0) {
				deleteState += listIdComputer.get(i) + " , ";
			} else {
				deleteState += "error, cannot Supress "+listIdComputer.get(i)+" ";
			}
		}

		setPageContent(search, computerPage.getIndexFirstPageElement(), computerPage.getNombreElementPerPage());

		model.addAttribute("computerDeleteSuccess", computerDeleteSuccess);
		model.addAttribute("deleteState", deleteState);
		model.addAttribute("search", search);
		model.addAttribute("numberElementPerPage", nombrElementPerPage);
		model.addAttribute("numberOfComputers", numberOfComputers);
		model.addAttribute("computers", computers);
		model.addAttribute("computerPage", computerPage);
		model.addAttribute("numberTotalOfPages", totalNumberOfPages());
		model.addAttribute("computers", computers);
		model.addAttribute("numberOfComputers", numberOfComputers);
		return "dashboard";
	}

	/**
	 * 
	 * @return totalNumberOfPages asName
	 */
	private int totalNumberOfPages() {
		return (int) Math
		    .ceil(computerPage.getNombreElementTotal() / Double.valueOf(computerPage.getNombreElementPerPage()));
	}

	/**
	 * 
	 * @param pSearch
	 *          name of computer or company
	 * @param pIndexFirstPageElement
	 *          asName
	 * @param pNombrElementPerPageasName
	 */
	private void setPageContent(String pSearch, int pIndexFirstPageElement, int pNombrElementPerPage) {
		if (!search.equals("")) {
			numberOfComputers = computerService.findByComputerAndCompanyName(pSearch).size();
			computers = computerService.findByComputerAndCompanyNameLimit(pSearch, pIndexFirstPageElement,
			    nombrElementPerPage);

		} else {
			numberOfComputers = computerService.findAll().size();
			computers = computerService.findLimitNumberOfResult(pIndexFirstPageElement, pNombrElementPerPage);

		}
	}
}
