package com.excilys.cdb.ui;

import java.util.ArrayList;
import java.util.List;

import com.excilys.cdb.model.Computer;
import com.excilys.cdb.service.ComputerService;

public class UIComputer {
	
	public static void listComputer() {
		ComputerService cs = new ComputerService();
		List<Computer> computers = new ArrayList<Computer>();
		computers = cs.findAll();
		
		for(Computer cpt : computers) {
			System.out.println("<-- "+cpt.getName()+" --> introduced = "+cpt.getIntroduced()+" | discontinued = "+cpt.getDiscontinued()+" | "+cpt.getCompany().getName()+" -->");
		}
	}
	
}
