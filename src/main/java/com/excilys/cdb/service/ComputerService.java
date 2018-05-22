package com.excilys.cdb.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.excilys.cdb.dao.ComputerDAO;
import com.excilys.cdb.exceptions.CdbException;
import com.excilys.cdb.model.Computer;
import com.excilys.cdb.validator.ComputerValidator;

/**
 * Computer service.
 *
 * @author sanogo
 *
 */
@Service
public class ComputerService {

	static final Logger LOGGER = LoggerFactory.getLogger(ComputerService.class);
	private ComputerValidator computerValidator = new ComputerValidator();
	@Autowired
	private ComputerDAO computerDAO;


	/**
	 * find computer by id.
	 *
	 * @param id
	 *          asName
	 * @return Computer
	 */
	public Computer findById(int id) {
		return computerDAO.findById(id).get();
	}

	/**
	 * find all computers.
	 *
	 * @return List<Computer>
	 */
	public List<Computer> findAll() {
		return computerDAO.findAll();
	}

	/**
	 * find a list of computers by theirs names.
	 *
	 * @param name
	 *          asName
	 * @return List<Computer>
	 */
	public List<Computer> findByName(String name) {
		return computerDAO.findByName(name);
	}

	/**
	 * find a list of computer by their company id.
	 *
	 * @param id
	 *          asName
	 * @return List<Computer>
	 */
	public List<Computer> findByCompany(int id) {
		return computerDAO.findByCompany(id);
	}

	/**
	 * find a list a computer to generate pages.
	 *
	 * @param pageIndex
	 *          asName
	 * @param numberOfResultByPage
	 *          asName
	 * @return List<Computer>
	 */
	public List<Computer> findLimitNumberOfResult(int pageIndex, int numberOfResultByPage) {
		return computerDAO.findLimitNumberOfResult(pageIndex, numberOfResultByPage);
	}

	/**
	 * Save computer.
	 *
	 * @param computer
	 *          asName
	 * @return (0 or 1)
	 * @throws CdbException
	 *           asName
	 */
	public int save(Computer computer) throws CdbException {
		if (computerValidator.verifComputerNameBeforeSave(computer.getName())
		    && computerValidator.verifDate(computer.getIntroduced(), computer.getDiscontinued())
		    && computerValidator.verifPresenceOfIllegalExpressionBeforeSave(computer.getName())) {
			return computerDAO.save(computer);
		} else {
			LOGGER.info("Name could not be saved");
			return 0;
		}
	}

	/**
	 * delete computer.
	 *
	 * @param id
	 *          asName
	 * @return (0 or 1)
	 */
	public int delete(int id) {
		if (findById(id).getId() == 0) {
			LOGGER.info("Le computer que vous voulez supprimer n'esxiste pas");
			return 0;
		} else {
			return computerDAO.delete(id);
		}
	}

	/**
	 * update computer.
	 *
	 * @param computer
	 *          asName
	 * @return (0 or 1)
	 * @throws CdbException
	 *           asName
	 */
	public int update(Computer computer) throws CdbException {

		if (findById(computer.getId()).getId() != 0 & computerValidator.verifComputerNameBeforeSave(computer.getName())
		    & computerValidator.verifDate(computer.getIntroduced(), computer.getDiscontinued())
		    & computerValidator.verifPresenceOfIllegalExpressionBeforeSave(computer.getName())) {
			return computerDAO.update(computer);
		} else {
			LOGGER.info("Le computer que vous voulez modifier n'esxiste pas");
			return 0;
		}

	}

	/**
	 * 
	 * @param name
	 * @param pageIndex
	 * @param numberOfResultByPage
	 * @return
	 */
	public List<Computer> findByComputerAndCompanyName(String name) {
		return computerDAO.findByComputerAndCompanyName(name);
	}

	/**
	 * 
	 * @param name
	 * @param pageIndex
	 * @param numberOfResultByPage
	 * @return
	 */
	public List<Computer> findByComputerAndCompanyNameLimit(String name, int pageIndex, int numberOfResultByPage) {
		return computerDAO.findByComputerAndCompanyNameLimit(name, pageIndex, numberOfResultByPage);
	}

}
