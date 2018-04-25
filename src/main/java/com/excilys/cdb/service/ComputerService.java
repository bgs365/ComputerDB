package com.excilys.cdb.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.excilys.cdb.dao.ComputerDAO;
import com.excilys.cdb.main.Main;
import com.excilys.cdb.model.Computer;
/**
 * Computer service.
 * 
 * @author sanogo
 *
 */
public enum ComputerService {
	INSTANCE;

	final static Logger LOGGER = LoggerFactory.getLogger(Main.class);

	/**
	 *find computer by id.
	 *
	 * @param id
	 * @return Computer
	 */
	public Computer findById(int id) {
		return ComputerDAO.INSTANCE.findById(id);
	}
	
	/**
	 *find all computers.
	 *
	 * @return List<Computer>
	 */
	public List<Computer> findAll() {
		return ComputerDAO.INSTANCE.findAll();
	}
	
	/**
	 *find a list of computers by theirs names.
	 *
	 * @param name
	 * @return  List<Computer>
	 */
	public List<Computer> findByName(String name) {
		return ComputerDAO.INSTANCE.findByName(name);
	}

	/**
	 *find a list of computer by their company id.
	 *
	 * @param id
	 * @return List<Computer>
	 */
	public List<Computer> findByCompany(int id) {
		return ComputerDAO.INSTANCE.findByCompany(id);
	}
	
	/**
	 *find a list a computer to generate pages.
	 *
	 * @param pageIndex
	 * @param numberOfResultByPage
	 * @return List<Computer>
	 */
	public List<Computer> findLimitNumberOfResult(int pageIndex, int numberOfResultByPage) {
		return ComputerDAO.INSTANCE.findLimitNumberOfResult(pageIndex, numberOfResultByPage);
	}
	
	/**
	 *Save computer. 
	 *
	 * @param computer
	 * @return (0 or 1)
	 */
	public int save(Computer computer) {
		if (computer.getName().length() < 5) {
			LOGGER.info("Le nom du computer doit faire au moins 5 carractères");
			return 0;
		} /*
			 * else if( ! computer.getDiscontinued().equals(null)) { if(
			 * computer.getIntroduced().isAfter( computer.getDiscontinued() ) ) {
			 * logger.info("La date d'intro doit être inferieur à la date de retrait");
			 * return 0; }else { return ComputerDAO.INSTANCE.save(computer); } }
			 */else {
			return ComputerDAO.INSTANCE.save(computer);
		}
	}
	
	/**
	 *delete computer.
	 *
	 * @param id
	 * @return (0 or 1)
	 */
	public int delete(int id) {
		if (findById(id).getId() == 0) {
			LOGGER.info("Le computer que vous voulez supprimer n'esxiste pas");
			return 0;
		} else {
			return ComputerDAO.INSTANCE.delete(id);
		}
	}
	
	/**
	 *update computer.
	 *
	 * @param computer
	 * @return (0 or 1)
	 */
	public int update(Computer computer) {
		if (findById(computer.getId()).getId() == 0) {
			LOGGER.info("Le computer que vous voulez modifier n'esxiste pas");
			return 0;
		} else {
			return ComputerDAO.INSTANCE.update(computer);
		}
	}

}
