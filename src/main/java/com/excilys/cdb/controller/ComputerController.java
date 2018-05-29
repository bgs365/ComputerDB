package com.excilys.cdb.controller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.excilys.cdb.dto.CompanyDTO;
import com.excilys.cdb.dto.ComputerDTO;
import com.excilys.cdb.exceptions.CdbException;
import com.excilys.cdb.model.Company;
import com.excilys.cdb.model.Computer;
import com.excilys.cdb.page.Page;
import com.excilys.cdb.service.CompanyService;
import com.excilys.cdb.service.ComputerService;
import com.excilys.cdb.validator.ComputerDTOValidator;
import com.excilys.com.mapper.CompanyMapper;

@Controller
@RequestMapping("/computer")
public class ComputerController {

	private int nombrElementPerPage = 10;
	private String search = "";
	private int numberOfComputers = 0;
	private List<Computer> computers;
	private List<CompanyDTO> companies;
	private Page<Computer> computerPage;
	@Autowired
	private ComputerService computerService;
	@Autowired
	private CompanyService companyService;
	private CompanyMapper companyMapper = new CompanyMapper();
	private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

	static final Logger LOGGER = LoggerFactory.getLogger(ComputerController.class);
	final private String DASHBOARD = "dashboard";
	final private String ADD_COMPUTER = "addComputer";
	final private String EDIT_COMPUTER = "editComputer";
	
	@InitBinder("ComputerDTO")
	protected void initBinder(WebDataBinder binder) {
	    binder.setValidator(new ComputerDTOValidator());
	}

	/**
	 * 
	 * @param model
	 * @return dashboard page
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String IndexComputer(Locale locale, ModelMap model) {
		setPageContent(search, 0, nombrElementPerPage);
		computerPage = new Page<Computer>(computers, nombrElementPerPage, numberOfComputers);

		defaultElementToSendToDashboard(model);
		return DASHBOARD;
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

		defaultElementToSendToDashboard(model);
		return DASHBOARD;
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

		setPageContent(search, 0, computerPage.getNombreElementPerPage());

		defaultElementToSendToDashboard(model);
		return DASHBOARD;
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

		defaultElementToSendToDashboard(model);
		return DASHBOARD;
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

		companies = companyMapper.mapCompanyToCompanyDTO(companyService.findAll());

		if (idComputerToModifie != 0) {
			int computerId = idComputerToModifie;
			model.addAttribute("ComputerDTO", new ComputerDTO());
			Computer computer = computerService.findById(computerId);
			model.addAttribute("computer", computer);
		}

		model.addAttribute("companies", companies);
		return EDIT_COMPUTER;
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
	public String editComputer(@ModelAttribute("ComputerDTO") @Validated(ComputerDTO.class) ComputerDTO computerDTO, BindingResult result,
	    ModelMap model) {
		
		model.addAttribute("companies", companies);
		if(result.hasErrors()) {
			return EDIT_COMPUTER;
		}
		
		String errors = "";
		boolean success = false;
		

		Computer computer = computerService.findById(computerDTO.getId());

		computer.setName(computerDTO.getName());
		if (!StringUtils.isBlank(computerDTO.getIntroduced())) {
			computer.setIntroduced(LocalDate.parse(computerDTO.getIntroduced(), formatter));
		}
		if (!StringUtils.isBlank(computerDTO.getDiscontinued())) {
			computer.setDiscontinued(LocalDate.parse(computerDTO.getDiscontinued(), formatter));
		}
		if (computerDTO.getCompanyId() != 0) {
			computer.setCompany(companyService.findById(computerDTO.getCompanyId()));
		} else {
			computer.setCompany(new Company());
		}

		try {

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
		return EDIT_COMPUTER;
	}

	/**
	 * Get.
	 * 
	 * @param model
	 * @return addComputer page
	 */
	@RequestMapping(value = "/addComputer", method = RequestMethod.GET)
	public String addComputerGetPage(ModelMap model) {
		companies = companyMapper.mapCompanyToCompanyDTO(companyService.findAll());
		model.addAttribute("ComputerDTO", new ComputerDTO());
		model.addAttribute("companies", companies);
		return ADD_COMPUTER;
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
	public String addComputer( @ModelAttribute("ComputerDTO") @Validated(ComputerDTO.class) ComputerDTO computerDTO, BindingResult result,
	    Locale locale, ModelMap model) {
		
		model.addAttribute("companies", companies);
		if(result.hasErrors()) {
			return EDIT_COMPUTER;
		}
		
		String errors = "";
		boolean success = false;
		companies = companyMapper.mapCompanyToCompanyDTO(companyService.findAll());
		Computer computer = new Computer();

		computer.setName(computerDTO.getName());
		if (!StringUtils.isBlank(computerDTO.getIntroduced())) {
			computer.setIntroduced(LocalDate.parse(computerDTO.getIntroduced(), formatter));
		}
		if (!StringUtils.isBlank(computerDTO.getDiscontinued())) {
			computer.setDiscontinued(LocalDate.parse(computerDTO.getDiscontinued(), formatter));
		}

		if (computerDTO.getCompanyId() != 0) {
			computer.setCompany(companyService.findById(computerDTO.getCompanyId()));
		} else {
			computer.setCompany(new Company());
		}

		try {
			if (computerService.save(computer) == 1) {
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
		return ADD_COMPUTER;
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
				deleteState += "error, cannot delete " + listIdComputer.get(i) + " ";
			}
		}

		setPageContent(search, computerPage.getIndexFirstPageElement(), computerPage.getNombreElementPerPage());

		model.addAttribute("computerDeleteSuccess", computerDeleteSuccess);
		model.addAttribute("deleteState", deleteState);
		defaultElementToSendToDashboard(model);
		return DASHBOARD;
	}

	/**
	 * Send default parameters to dashboard jsp.
	 * @param model
	 */
	private void defaultElementToSendToDashboard(ModelMap model) {
		model.addAttribute("search", search);
		model.addAttribute("numberElementPerPage", nombrElementPerPage);
		model.addAttribute("numberOfComputers", numberOfComputers);
		model.addAttribute("computers", computers);
		model.addAttribute("computerPage", computerPage);
		model.addAttribute("numberTotalOfPages", totalNumberOfPages());
		model.addAttribute("computers", computers);
		model.addAttribute("numberOfComputers", numberOfComputers);
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
	 * @param pSearch name of computer or company
	 * @param pIndexFirstPageElement asName
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
