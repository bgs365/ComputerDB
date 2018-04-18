package com.excilys.cdb.ui;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.excilys.cdb.model.Company;
import com.excilys.cdb.model.Computer;
import com.excilys.cdb.service.CompanyService;
import com.excilys.cdb.service.ComputerService;

public class UIComputer {
	private static ComputerService computerService = new ComputerService();
	private static CompanyService companyService = new CompanyService();
	private static Scanner sc;
	
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
		sc = new Scanner(System.in);
		Computer computer = new Computer();
		String nom = null;
		LocalDate IntroducedDate = null;
		LocalDate discontinuedDate = null;
		Company company = null;
		
		System.out.println("******************* Création de PC *******************");	

		System.out.println("--> Entrez le nom du PC : ");
		nom = saisieNom();
		System.out.println("--> Nom = "+nom);
		
		System.out.println("--> Entrez la date d'introduction : ");
		IntroducedDate = saisieDate();
		System.out.println("--> Date d'intro = "+IntroducedDate);
		
		System.out.println("--> Entrez la date de retait : ");
		discontinuedDate = saisieDate();
		System.out.println("--> Date de retrait = "+discontinuedDate);
			
		System.out.println("--> Entrez le nom de la company : ");
		company = saisirCompany();
		System.out.println("--> Company = "+company);
		
		computer.setName(nom);
		computer.setIntroduced(IntroducedDate);
		computer.setDiscontinued(discontinuedDate);
		computer.setCompany(company);
		
		computerService.save(computer);
	
	}
	
	public static void main(String[] args) {
		
		createComputer();
	}
	
	public static String saisieNom() {
		String nom = null;
		
		do {
			System.out.println("Le nom doit avoir plus de 3 carractère !");
			nom = sc.nextLine();
		}while(nom.length() < 5);
		return nom;
	}

	/*
	 * Permet à l'utilisateur de saisir une date
	 * Verifie la date saisie par l'utilisateur, et retourne une date au bon format
	 * */
	private static LocalDate saisieDate() {
		
		LocalDate localDate = null;
		boolean dateValide = false;
		String uDate = null;
		
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        sdf.setLenient(false);
        System.out.println("format accepté : (dd/mm/yyyy) || Vous pouvez aussi mettre nc si vous voulez pas entrez de date");
       
        do {
        	uDate = sc.nextLine();
        	if(!uDate.equals("nc") )
        	{
        		 try{ 
     	            sdf.parse(uDate);
     	            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
     	        	localDate = LocalDate.parse(uDate, formatter);
     	        	dateValide = true;
     	        }
     	        catch(ParseException e)
     	        {
     	            System.out.println(uDate+" n'est pas une date valid");  	            
     	        }
        	}else {
        		dateValide = true;
    		}
	       
        }while(!dateValide);
        return localDate;
        
	}
	
	/*
	 * Permet à l'utilisateur de saisir une company
	 * Verifie si la company existe dans la bdd, sinon demande de ressaisir une company valid
	 * renvoie une company null quand l'utilisateur entre "nc"
	 * */
	private static Company saisirCompany() {
		
		Company company = null;
		boolean companyValide = false;
		String nomCompany = null;
		
		do {
			System.out.println("Entrez une company existante ou *nc* pour aucune company");
			nomCompany = sc.nextLine();
			if(!nomCompany.equals("nc") ){
				company = companyService.findbyName(nomCompany);
				if(company.getId() != 0) {
					companyValide = true;
				}else {
					System.out.println("Cette Company n'existe Pas");
					company = null;
				}
        	}else {
        		companyValide = true;
        	}
			
		}while(!companyValide);
		
		
		return company;
	}
}
