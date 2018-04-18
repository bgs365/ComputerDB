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
	
	public static void updateComputer() {
		Computer computer = new Computer();
		String nom = null;
		LocalDate IntroducedDate = null;
		LocalDate discontinuedDate = null;
		Company company = null;
		String choix = null;
				
		System.out.println("******************* Entrez l'id du pc à Modifier *******************");
		computer = VerificateurDeSaisie.saisirComputerATrouver();
		System.out.println("Le PC choisi est --> "+computer);
		
		if(computer!=null) {
			System.out.println("--> Voulez vous modifier le nom ?");
			choix = VerificateurDeSaisie.choixBinaire();
			if(choix.equals("1")) {
				System.out.println("--> Entrez le nom du PC : ");
				nom = VerificateurDeSaisie.saisieNom();
				System.out.println("--> Nom = "+nom);
				computer.setName(nom);
			}
			
			System.out.println("--> Voulez vous modifier la date d'introduction ?");
			choix = VerificateurDeSaisie.choixBinaire();
			if(choix.equals("1")) {
				System.out.println("--> Entrez la date d'introduction : ");
				IntroducedDate = VerificateurDeSaisie.saisieDate();
				System.out.println("--> Date d'intro = "+IntroducedDate);
				computer.setIntroduced(IntroducedDate);
			}
			
			System.out.println("--> Voulez vous modifier la date de retait ?");
			choix = VerificateurDeSaisie.choixBinaire();
			if(choix.equals("1")) {
				System.out.println("--> Entrez la date de retait : ");
				discontinuedDate = VerificateurDeSaisie.saisieDate();
				System.out.println("--> Date de retrait = "+discontinuedDate);
				computer.setDiscontinued(discontinuedDate);
			}
			
			System.out.println("--> Voulez vous modifier le nom de company ?");
			choix = VerificateurDeSaisie.choixBinaire();
			if(choix.equals("1")) {
				System.out.println("--> Entrez le nom de la company : ");
				company = VerificateurDeSaisie.saisirCompany();
				System.out.println("--> Company = "+company);
				computer.setCompany(company);
				computerService.updateInfos(computer);
				computerService.updateCompany(computer, company);
			}else {
				computerService.updateInfos(computer);
			}
		}
		
		
		
	}
	
	public static void deleteComputer() {
		Computer computer = new Computer();
		String choix = null;
				
		System.out.println("******************* Entrez l'id du pc à Suprimer *******************");
		computer = VerificateurDeSaisie.saisirComputerATrouver();
		System.out.println("Voulez vous vraiment effacer --> "+computer);
		choix = VerificateurDeSaisie.choixBinaire();
		if(choix.equals("1")) {
			computerService.delete(computer);
		}else {
			System.out.println("OK");
		}
		
	}
	
	public static void main(String[] args) {
		deleteComputer();
	}
	
	
}
