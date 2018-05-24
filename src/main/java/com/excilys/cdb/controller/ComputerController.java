package com.excilys.cdb.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.excilys.cdb.model.Computer;
import com.excilys.cdb.page.Page;
import com.excilys.cdb.service.ComputerService;

@Controller
@RequestMapping("/computer")
public class ComputerController {

	@Autowired
	private ComputerService computerService;
	private int nombrElementPerPage = 10;
	private int numberOfComputers = 0;
	private List<Computer> computers;
	private Page<Computer> computerPage;
	private String search = "";
	static final Logger LOGGER = LoggerFactory.getLogger(ComputerController.class);
	
	/**
	 * 
	 * @param model
	 * @return dashboard page
	 */
	@RequestMapping(value = "/",method = RequestMethod.GET)
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

		computerPage.setCurrentPage( pageNumber);
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
	 * @param numberElementDisplayed
	 * @param model
	 * @return dashboard page
	 */
	@RequestMapping(value = "/nombreElementPerPage", method = RequestMethod.GET)
	public String changeNumberOfElementDisplayPerPage(@RequestParam(value = "buttonSetNumberElementDisplayed") int numberElementDisplayed, ModelMap model) {

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
			LOGGER.debug("list computer for if "+computers);

		} else {
			numberOfComputers = computerService.findAll().size();
			computers = computerService.findLimitNumberOfResult(pIndexFirstPageElement, pNombrElementPerPage);
			LOGGER.debug("list computer for else "+computers);


		}
	}
}
