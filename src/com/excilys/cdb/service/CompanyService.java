package com.excilys.cdb.service;

import java.util.ArrayList;
import java.util.List;

import com.excilys.cdb.dao.CompanyDAO;
import com.excilys.cdb.model.Company;

public class CompanyService {
	
	private CompanyDAO companyDAO;
	
	
	public CompanyService() {
		companyDAO = new CompanyDAO();
	}
	
	public Company findById(int id) {
		Company company = null;
		company = companyDAO.findById(id);
		return company;
	}
	
	public List<Company> findAll() {
		List<Company> companies = new ArrayList<Company>();
		companies = companyDAO.findAll();
		return companies;
	}
	
	public List<Company> findLimitNumberOfResult(int pageIndex, int numberOfResultByPage) {
		List<Company> companies = new ArrayList<Company>();
		companies = companyDAO.findLimitNumberOfResult(pageIndex, numberOfResultByPage);
		return companies;
	}
	
	public Company findbyName(String name) {
		Company company = new Company();
		Company companyToFind = new Company(1,name);
		company = companyDAO.findByName(companyToFind);
		return company;//Parceque le nom est prompre à la company
	}
	
		
}
