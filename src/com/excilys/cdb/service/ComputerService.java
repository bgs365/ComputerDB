package com.excilys.cdb.service;

import java.util.List;
import com.excilys.cdb.dao.ComputerDAO;
import com.excilys.cdb.model.Computer;

public enum ComputerService {
	INSTANCE;
	
	
	public Computer findById(int id) {
		return ComputerDAO.INSTANCE.findById(id);
	}
	
	public List<Computer> findAll() {
		return ComputerDAO.INSTANCE.findAll();
	}
	
	public List<Computer> findByName(String name){
		return ComputerDAO.INSTANCE.findByName(new Computer(0,name,null,null));
	}
	
	public List<Computer> findByCompany(int id){
		return ComputerDAO.INSTANCE.findByCompany(id);
	}
	
	public List<Computer> findLimitNumberOfResult(int pageIndex, int numberOfResultByPage) {
		return ComputerDAO.INSTANCE.findLimitNumberOfResult(pageIndex, numberOfResultByPage);
	}
	
	public void save(Computer computer) {
		ComputerDAO.INSTANCE.save(computer);
	}
	
	public void delete(Computer computer) {
		ComputerDAO.INSTANCE.delete(computer);
	}
	
	public void update(Computer computer) {
		ComputerDAO.INSTANCE.update(computer);
	}
	
	
}
