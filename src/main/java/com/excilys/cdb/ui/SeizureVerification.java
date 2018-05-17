package com.excilys.cdb.ui;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.excilys.cdb.model.Company;
import com.excilys.cdb.model.Computer;
import com.excilys.cdb.service.CompanyService;
import com.excilys.cdb.service.ComputerService;
import com.excilys.cdb.springConfig.ApplicationConfig;

/**
 * Class which help and verify data enter by user.
 *
 * @author sanogo
 *
 */
public class SeizureVerification {

	static ApplicationContext context = new AnnotationConfigApplicationContext(ApplicationConfig.class);
	static CompanyService companyService = (CompanyService) context.getBean(CompanyService.class);
	static ComputerService computerService = (ComputerService) context.getBean(ComputerService.class);
	private static Scanner sc = new Scanner(System.in);

	/**
	 * Verify an mane gave by user to an computer.
	 *
	 * @return nom
	 */
	public static String saisieNom() {
		String nom = null;

		do {
			System.out.println("Le nom doit avoir plus de 5 carractère !");
			nom = sc.nextLine();
		} while (nom.length() < 5);
		return nom;
	}

	/**
	 * allow to an user to enter date. verify the format of date enter by user,
	 * until date have an correct one. user can choose to enter no date by choosing
	 * "nc"
	 *
	 * @return localDate
	 */
	public static LocalDate saisieDate() {

		LocalDate localDate = null;
		boolean dateValide = false;
		String uDate = null;

		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		sdf.setLenient(false);
		System.out
		    .println("format accepté : (dd/mm/yyyy) || Vous pouvez aussi mettre nc si vous voulez pas entrez de date");

		do {
			uDate = sc.nextLine();
			if (!uDate.equals("nc")) {
				try {
					sdf.parse(uDate);
					DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
					localDate = LocalDate.parse(uDate, formatter);
					dateValide = true;
				} catch (ParseException e) {
					System.out.println(uDate + " n'est pas une date valid");
				}
			} else {
				dateValide = true;
			}

		} while (!dateValide);
		return localDate;
	}

	/**
	 * allow user to enter company. verify existance of company. continue to asking
	 * a valid one until the user gave a valid one. user cans qhose no company by
	 * choosing "nc"
	 *
	 * @return Company
	 */
	public static Company saisirCompany() {

		Company company = null;
		boolean companyValide = false;
		String nomCompany = null;

		do {
			System.out.println("Entrez une company existante ou *nc* pour aucune company");
			nomCompany = sc.nextLine();
			if (!nomCompany.equals("nc")) {
				company = companyService.findbyName(nomCompany).get(0);
				if (company.getId() != 0) {
					companyValide = true;
				} else {
					System.out.println("Cette Company n'existe Pas");
					company = null;
				}
			} else {
				companyValide = true;
			}

		} while (!companyValide);

		return company;
	}

	/**
	 * Allow user to enter an company. use the same process of verification as
	 * previous one.
	 *
	 * @return Computer
	 */
	public static Computer saisirComputerATrouver() {
		Computer computer = new Computer();
		boolean computerValide = false;
		int idComputer = 0;
		do {
			System.out.println("Entrez une computer existante ou *0* pour sortir");
			idComputer = sc.nextInt();
			if (idComputer != 0) {
				computer = computerService.findById(idComputer);
				if (computer.getId() != 0) {
					computerValide = true;
				} else {
					System.out.println("Cet pc n'existe Pas");
					computer = null;
				}
			} else {
				computerValide = true;
			}
		} while (!computerValide);
		return computer;
	}

	/**
	 * as previous but for company.
	 * 
	 * @return Company valid company
	 */
	public static Company saisirCompanyATrouver() {
		Company company = new Company();
		boolean companyValide = false;
		int idCompany = 0;
		do {
			System.out.println("Entrez une computer existante ou *0* pour sortir");
			idCompany = sc.nextInt();
			if (idCompany != 0) {
				company = companyService.findById(idCompany);
				if (company.getId() != 0) {
					companyValide = true;
				} else {
					System.out.println("Cet pc n'existe Pas");
				}
			} else {
				companyValide = true;
			}
		} while (!companyValide);
		return company;
	}

	/**
	 * Allow user to choice yes or no.
	 *
	 * @return String
	 */
	public static String choixBinaire() {
		String choix = null;
		boolean choixValide = false;
		do {
			System.out.println("Entre : 1--> Oui - 2-->Non");
			choix = sc.nextLine();
			if (choix.equals("1") | choix.equals("2")) {
				choixValide = true;
			}

		} while (!choixValide);
		return choix;
	}

}