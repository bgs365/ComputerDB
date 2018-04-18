package com.excilys.cdb.ui;

import java.util.ArrayList;
import java.util.List;

import com.excilys.cdb.model.Computer;
import com.excilys.cdb.service.ComputerService;

public class UIComputer {
	private static ComputerService cs = new ComputerService();
	
	public static void listComputer() {
		
		List<Computer> computers = new ArrayList<Computer>();
		computers = cs.findAll();
		System.out.println("******************* La liste des PC *******************");
		for(Computer cpt : computers) {
			System.out.println("<-- "+cpt.getName()+" --> introduced = "+cpt.getIntroduced()+" | discontinued = "+cpt.getDiscontinued()+" | "+cpt.getCompany().getName()+" -->");
		}
		System.out.println("******************* "+computers.size()+" PC *******************");
		
	}
	
	public static void createComputer() {
		
	}
	
}
