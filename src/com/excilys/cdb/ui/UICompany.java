package com.excilys.cdb.ui;

import java.util.ArrayList;
import java.util.List;

import com.excilys.cdb.model.Company;
import com.excilys.cdb.service.CompanyService;

public class UICompany {
	
	public static void listCompany() {
		CompanyService cs = new CompanyService();
		List<Company> companies = new ArrayList<Company>();
		companies = cs.findAll();
		
		for(Company cpn : companies) {
			System.out.println("-->"+cpn.getName());
		}
	}
}
