package com.excilys.cdb.ui;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

import com.excilys.cdb.model.Company;
import com.excilys.cdb.model.Computer;
import com.excilys.cdb.service.CompanyService;
import com.excilys.cdb.service.ComputerService;

public class SeizureVerification {
	private static CompanyService companyService = CompanyService.INSTANCE;
	private static ComputerService computerService = ComputerService.INSTANCE;
	private static Scanner sc = new Scanner(System.in);

	public SeizureVerification() {
	}

	/*
	 * Permet de rentrer et de verifier le nom entré par l'utilisateur
	 */
	public static String saisieNom() {
		String nom = null;

		do {
			System.out.println("Le nom doit avoir plus de 5 carractère !");
			nom = sc.nextLine();
		} while (nom.length() < 5);
		return nom;
	}

	/*
	 * Permet à l'utilisateur de saisir une date Verifie la date saisie par
	 * l'utilisateur, et retourne une date au bon format
	 */
	public static LocalDate saisieDate() {

		LocalDate localDate = null;
		boolean dateValide = false;
		String uDate = null;

		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		sdf.setLenient(false);
		System.out.println(
				"format accepté : (dd/mm/yyyy) || Vous pouvez aussi mettre nc si vous voulez pas entrez de date");

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

	/*
	 * Permet à l'utilisateur de saisir une company Verifie si la company existe
	 * dans la bdd, sinon demande de ressaisir une company valid renvoie une company
	 * null quand l'utilisateur entre "nc"
	 */
	public static Company saisirCompany() {

		Company company = null;
		boolean companyValide = false;
		String nomCompany = null;

		do {
			System.out.println("Entrez une company existante ou *nc* pour aucune company");
			nomCompany = sc.nextLine();
			if (!nomCompany.equals("nc")) {
				company = companyService.findbyName(nomCompany);
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

	public static Computer saisirComputerATrouver() {
		Computer computer = null;
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
					System.out.println("Cette Company n'existe Pas");
					computer = null;
				}
			} else {
				computerValide = true;
			}
		} while (!computerValide);
		return computer;
	}

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

	public static void main(String[] args) {
		// choixBinaire();
	}
}
