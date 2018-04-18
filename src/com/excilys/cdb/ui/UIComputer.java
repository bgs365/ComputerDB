package com.excilys.cdb.ui;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.excilys.cdb.model.Company;
import com.excilys.cdb.model.Computer;
import com.excilys.cdb.service.ComputerService;

public class UIComputer {
	private static ComputerService computerService = new ComputerService();
	
	public static void listComputer() {
		
		List<Computer> computers = new ArrayList<Computer>();
		computers = computerService.findAll();
		System.out.println("******************* La liste des PC *******************");
		for(Computer cpt : computers) {
			System.out.println("<-- "+cpt.getName()+" --> introduced = "+cpt.getIntroduced()+" | discontinued = "+cpt.getDiscontinued()+" | "+cpt.getCompany().getName()+" -->");
		}
		System.out.println("******************* "+computers.size()+" PC *******************");
		
	}
	
	public static void createComputer() {
		
		Computer computer = new Computer();
		String nom = null;
		LocalDate IntroducedDate = null;
		LocalDate discontinuedDate = null;
		Company company = null;
		
		System.out.println("******************* Création de PC *******************");	

		System.out.println("--> Entrez le nom du PC : ");
		nom = VerificateurDeSaisie.saisieNom();
		System.out.println("--> Nom = "+nom);
		
		System.out.println("--> Entrez la date d'introduction : ");
		IntroducedDate = VerificateurDeSaisie.saisieDate();
		System.out.println("--> Date d'intro = "+IntroducedDate);
		
		System.out.println("--> Entrez la date de retait : ");
		discontinuedDate = VerificateurDeSaisie.saisieDate();
		System.out.println("--> Date de retrait = "+discontinuedDate);
			
		System.out.println("--> Entrez le nom de la company : ");
		company = VerificateurDeSaisie.saisirCompany();
		System.out.println("--> Company = "+company);
		
		computer.setName(nom);
		computer.setIntroduced(IntroducedDate);
		computer.setDiscontinued(discontinuedDate);
		computer.setCompany(company);
		
		computerService.save(computer);
	}
	
	public static void main(String[] args) {
	}
	
	
}
