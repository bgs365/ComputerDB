package com.excilys.cdb.service;

import java.util.ArrayList;
import java.util.List;

import com.excilys.cdb.dao.ComputerDAO;
import com.excilys.cdb.model.Company;
import com.excilys.cdb.model.Computer;

public class ComputerService {
	private ComputerDAO computerDAO;
	
	public ComputerService() {
		computerDAO = new ComputerDAO();
	}
	
	public Computer findById(int id) {
		Computer computer = null;
		computer = computerDAO.findById(id);
		return computer;
	}
	
	public List<Computer> findAll() {
		List<Computer> computers = new ArrayList<Computer>();
		computers = computerDAO.findAll();
		return computers;
	}
	
	public List<Computer> findByName(String name){
		List<Computer> computers = new ArrayList<Computer>();
		computers = computerDAO.findByName(new Computer(0,name,null,null));
		return computers;
	}
	
	public List<Computer> findByCompany(int id){
		List<Computer> computers = new ArrayList<Computer>();
		computers = computerDAO.findByCompany(new Company(id ,null) );
		return computers;
	}
	
	public List<Computer> findLimitNumberOfResult(int pageIndex, int numberOfResultByPage) {
		List<Computer> computers = new ArrayList<Computer>();
		computers = computerDAO.findLimitNumberOfResult(pageIndex, numberOfResultByPage);
		return computers;
	}
	
	public void save(Computer computer) {
		computerDAO.save(computer);
	}
	
	public void delete(Computer computer) {
		computerDAO.delete(computer);
	}
	
	public void updateInfos(Computer computer) {
		computerDAO.update(computer);
	}
	
	public void updateCompany(Computer computer, Company company) {
		computerDAO.updateCompany(computer, company);
	}
	
	
	
}
