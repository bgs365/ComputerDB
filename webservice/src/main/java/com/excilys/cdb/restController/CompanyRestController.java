package com.excilys.cdb.restController;

import java.util.List;
import java.util.NoSuchElementException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.excilys.cdb.model.Company;
import com.excilys.cdb.service.CompanyService;

import javassist.NotFoundException;

@RestController
@RequestMapping("/company")
public class CompanyRestController {
	private CompanyService companyService;

	static final Logger LOGGER = LoggerFactory.getLogger(CompanyRestController.class);

	private CompanyRestController(CompanyService companyService) {
		this.companyService = companyService;
	}
	
	/**
	 * 
	 * @param id
	 * @return
	 * @throws NotFoundException
	 */
	@RequestMapping(path = "/{id}", method = RequestMethod.GET)
	@ResponseBody
	public Company getCompany(@PathVariable("id") Long id) {
		Company company = new Company();
		try {
			company = companyService.findById(id);
		} catch (NoSuchElementException e) {
			e.getMessage();
			company.setName("Not exist");
		}
		return company;
	}
	
	/**
	 * all computers.
	 * @return
	 */
	@RequestMapping(method = RequestMethod.GET)
	@ResponseBody
	public List<Company> getAllComputers() {
		return companyService.findAll();
	}

}
