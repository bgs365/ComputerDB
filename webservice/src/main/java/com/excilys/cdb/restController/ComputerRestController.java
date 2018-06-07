package com.excilys.cdb.restController;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.excilys.cdb.exceptions.CdbException;
import com.excilys.cdb.model.Computer;
import com.excilys.cdb.service.ComputerService;

import javassist.NotFoundException;

@RestController
@RequestMapping("/computer")
public class ComputerRestController {

	private ComputerService computerService;

	static final Logger LOGGER = LoggerFactory.getLogger(ComputerRestController.class);

	private ComputerRestController(ComputerService computerService) {
		this.computerService = computerService;
	}

	/**
	 * 
	 * @param id
	 * @return
	 * @throws NotFoundException
	 */
	@RequestMapping(path = "/{id}", method = RequestMethod.GET)
	@ResponseBody
	public Computer getComputer(@PathVariable("id") Long id) {
		Computer computer = new Computer();
		try {
			computer = computerService.findById(id);
		} catch (NoSuchElementException e) {
			e.getMessage();
			computer.setName("Not exist");
		}
		return computer;
	}
	
	@RequestMapping(params = { "search","page", "size" }, method = RequestMethod.GET )
	@ResponseBody
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public List< Computer > findPaginated(@RequestParam( "search" ) String search,
	 @RequestParam( "page" ) int page, @RequestParam( "size" ) int size,
	 UriComponentsBuilder uriBuilder, HttpServletResponse response ){
	 
	   Page< Computer > resultPage = computerService.findByComputerAndCompanyNameLimit(search, page, size);
	   if( page > resultPage.getTotalPages() ) {
	  	 return new ArrayList<Computer>();
	   }
	 
	   return resultPage.getContent();
	}

	/**
	 * all computers.
	 * @return
	 */
	@RequestMapping(method = RequestMethod.GET)
	@ResponseBody
	public List<Computer> getAllComputers() {
		return computerService.findAll();
	}

	/**
	 * 
	 * @param computer
	 * @return
	 */
	@RequestMapping(method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.CREATED)
	@ResponseBody
	public String createComputer(@RequestBody Computer computer) {
		try {
			return computerService.save(computer).toString();
		} catch (CdbException e) {
			return e.getMessage();
		}
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	@ResponseStatus(HttpStatus.OK)
	public String update(@PathVariable("id") Long id, @RequestBody Computer computer) {
		try {
			return computerService.update(computer).toString();
		} catch (CdbException e) {
			return e.getMessage();
		}
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.OK)
	public void delete(@PathVariable("id") Long id) {
		computerService.delete(id);
	}

}
