package com.excilys.cdb.ui;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

import com.excilys.cdb.model.Company;
import com.excilys.cdb.service.CompanyService;

public class UICompany {

	static CompanyService cs = new CompanyService();
	public static void listCompany() {
		List<Company> companies = new ArrayList<Company>();
		companies = cs.findAll();
		
		for(Company cpn : companies) {
			System.out.println("-->"+cpn.getName());
		}
	}

}
