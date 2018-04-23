package com.excilys.cdb.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.excilys.cdb.dao.ComputerDAO;
import com.excilys.cdb.main.Main;
import com.excilys.cdb.model.Computer;

public enum ComputerService {
	INSTANCE;

	final static Logger logger = LoggerFactory.getLogger(Main.class);

	public Computer findById(int id) {
		return ComputerDAO.INSTANCE.findById(id);
	}

	public List<Computer> findAll() {
		return ComputerDAO.INSTANCE.findAll();
	}

	public List<Computer> findByName(String name) {
		return ComputerDAO.INSTANCE.findByName(name);
	}

	public List<Computer> findByCompany(int id) {
		return ComputerDAO.INSTANCE.findByCompany(id);
	}

	public List<Computer> findLimitNumberOfResult(int pageIndex, int numberOfResultByPage) {
		return ComputerDAO.INSTANCE.findLimitNumberOfResult(pageIndex, numberOfResultByPage);
	}

	public int save(Computer computer) {
		if(computer.getName().length() < 5 ) {
			logger.info("Le nom du computer doit faire au moins 5 carractères");
			return 0;
		}else if(computer.getIntroduced().isBefore( computer.getDiscontinued() ) ) {
			logger.info("La date d'intro doit être inferieur à la date de retrait");
			return 0;
		}else {
			return ComputerDAO.INSTANCE.save(computer);
		}
	}

	public int delete(Computer computer) {
		if( findById(computer.getId()).getId() == 0 ) {
			logger.info("Le computer que vous voulez supprimer n'esxiste pas");
			return 0;
		}else {
			return ComputerDAO.INSTANCE.delete(computer);
		}
	}

	public int update(Computer computer) {
		if( findById(computer.getId()).getId() == 0 ) {
			logger.info("Le computer que vous voulez modifier n'esxiste pas");
			return 0;
		}else {
			return ComputerDAO.INSTANCE.update(computer);
		}
	}

}
