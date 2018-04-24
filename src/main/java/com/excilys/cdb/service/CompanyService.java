package com.excilys.cdb.service;

import java.util.List;

import com.excilys.cdb.dao.CompanyDAO;
import com.excilys.cdb.model.Company;

public enum CompanyService {
	INSTANCE;

	public Company findById(int id) {
		return CompanyDAO.INSTANCE.findById(id);
	}

	public List<Company> findAll() {
		return CompanyDAO.INSTANCE.findAll();
	}

	public List<Company> findLimitNumberOfResult(int pageIndex, int numberOfResultByPage) {
		return CompanyDAO.INSTANCE.findLimitNumberOfResult(pageIndex, numberOfResultByPage);
	}

	public Company findbyName(String name) {
		return CompanyDAO.INSTANCE.findByName(name);
	}

}
