package com.excilys.cdb.ui;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

import org.apache.http.client.ClientProtocolException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.excilys.cdb.model.Company;
import com.excilys.cdb.model.Computer;
import com.excilys.cdb.service.CompanyService;
import com.excilys.cdb.service.ComputerService;
import com.excilys.cdb.springConfig.ApplicationConfig;

/**
 * Computer User Interface class.
 *
 * @author sanogo
 *
 */
public class ComputerUI {
	private SeizureVerification seizureVerification =  new SeizureVerification();
	static ApplicationContext context = new AnnotationConfigApplicationContext(ApplicationConfig.class);
	static CompanyService companyService = (CompanyService) context.getBean(CompanyService.class);
	static ComputerService computerService = (ComputerService) context.getBean(ComputerService.class);
	private Scanner scComputer = new Scanner(System.in);
	private int nombrElementParPage = 50;
	RestClientComputer restClientComputer = new RestClientComputer();
	static final Logger LOGGER = LoggerFactory.getLogger(ComputerUI.class);

	/**
	 * display a page of computer, allow to navigate between pages of computers.
	 * @throws IOException
	 * @throws ClientProtocolException
	 */
	public void listComputer() throws ClientProtocolException, IOException {

		int page = 0;
		int size = nombrElementParPage;
		List<Computer> computerPage = restClientComputer.getComputerPage("", page, size);
		String choix = "";

		System.out.println("******************* Liste of Computers *******************");
		System.out.println("Page n° " + page);
		System.out.println(display(computerPage));
		System.out.println(" << Page precedente 1 ** 0  Quitter ** 2 Page suivante >> ");
		boolean quiter = false;
		do {
			choix = scComputer.nextLine();
			if (choix.equals("1") | choix.equals("2") | choix.equals("0")) {
				if (choix.equals("1") | choix.equals("2")) {
					switch (choix) {

						case "1":
							if (page > 0) {
								page--;
								System.out.println("Page n° " + page);
								computerPage = restClientComputer.getComputerPage("", page, size);
								System.out.println(display(computerPage));
								System.out.println(" << Page precedente 1 ** 0  Quitter ** 2 Page suivante >> ");
							} else {
								System.out.println(" You can't reach this page ");
							}
						break;
						
						case "2":
							page++;
							System.out.println("Page n° " + page);
							computerPage = restClientComputer.getComputerPage("", page, size);
							System.out.println(display(computerPage));
							System.out.println(" << Page precedente 1 ** 0  Quitter ** 2 Page suivante >> ");
						break;

					}
					quiter = false;
				} else {
					quiter = true;
				}
			} else {
				System.out.println("Choix non valide");
				quiter = false;
			}

		} while (!quiter);

		for (Computer cpt : computerPage) {
			System.out.println("<-- " + cpt.getName() + " --> introduced = " + cpt.getIntroduced() + " | discontinued = "
			    + cpt.getDiscontinued() + " | " + " -->");
		}
		System.out.println("******************* " + computerPage + " PC *******************");

	}

	/**
	 * take a group of computers and range them in a string.
	 *
	 * @param computerPage List<Computer>
	 * @return String
	 */
	private static String display(List<Computer> computerPage) {
		String valeurRetour = "";
		for (Computer indexOfComputers : computerPage) {
			valeurRetour += "" + indexOfComputers.getId() + " -- "
			    + (indexOfComputers.getName() != null ? "" + indexOfComputers.getName() + " -- " : "")
			    + (indexOfComputers.getIntroduced() != null ? " " + indexOfComputers.getIntroduced() + " -- " : "")
			    + (indexOfComputers.getDiscontinued() != null ? " " + indexOfComputers.getDiscontinued() + " -- " : "")
			    + "]\n";
		}
		return valeurRetour;
	}

	/**
	 * reate computer menu.
	 */
	public void createComputer() {

		Computer computer = new Computer();
		String nom = "";
		LocalDate introducedDate;
		LocalDate discontinuedDate;
		Company company = new Company();

		System.out.println("******************* Création de PC *******************");

		System.out.println("--> Entrez le nom du PC : ");
		nom = seizureVerification.saisieNom();
		System.out.println("--> Nom = " + nom);

		System.out.println("--> Entrez la date d'introduction : ");
		introducedDate = seizureVerification.saisieDate();
		System.out.println("--> Date d'intro = " + introducedDate);

		System.out.println("--> Entrez la date de retait : ");
		discontinuedDate = seizureVerification.saisieDate();
		System.out.println("--> Date de retrait = " + discontinuedDate);

		System.out.println("--> Entrez le nom de la company : ");
		// company = seizureVerification.saisirCompany();
		System.out.println("--> Company = " + company);

		computer.setName(nom);
		computer.setIntroduced(introducedDate);
		computer.setDiscontinued(discontinuedDate);
		// computer.setCompany(company);

		restClientComputer.saveComputer(computer);

	}

	/**
	 * update computer menu.
	 */
	public void updateComputer() {
		Computer computer = new Computer();
		String nom = "";
		LocalDate introducedDate;
		LocalDate discontinuedDate;
		Company company = new Company();
		String choix = "";

		System.out.println("******************* Entrez l'id du pc à Modifier *******************");
		computer = seizureVerification.saisirComputerATrouver();
		System.out.println("Le PC choisi est --> " + computer);

		if (computer != null) {
			System.out.println("--> Voulez vous modifier le nom ?");
			choix = seizureVerification.choixBinaire();
			if (choix.equals("1")) {
				System.out.println("--> Entrez le nom du PC : ");
				nom = seizureVerification.saisieNom();
				System.out.println("--> Nom = " + nom);
				computer.setName(nom);
			}

			System.out.println("--> Voulez vous modifier la date d'introduction ?");
			choix = seizureVerification.choixBinaire();
			if (choix.equals("1")) {
				System.out.println("--> Entrez la date d'introduction : ");
				introducedDate = seizureVerification.saisieDate();
				System.out.println("--> Date d'intro = " + introducedDate);
				computer.setIntroduced(introducedDate);
			}

			System.out.println("--> Voulez vous modifier la date de retait ?");
			choix = seizureVerification.choixBinaire();
			if (choix.equals("1")) {
				System.out.println("--> Entrez la date de retait : ");
				discontinuedDate = seizureVerification.saisieDate();
				System.out.println("--> Date de retrait = " + discontinuedDate);
				computer.setDiscontinued(discontinuedDate);
			}

			System.out.println("--> Voulez vous modifier le nom de company ?");
			choix = seizureVerification.choixBinaire();
			if (choix.equals("1")) {
				System.out.println("--> Entrez le nom de la company : ");
				company = seizureVerification.saisirCompany();
				System.out.println("--> Company = " + company);
				computer.setCompany(company);
			}
			
			restClientComputer.updateComputer(computer);
		}

	}

	/**
	 * delete computer menu.
	 */
	public void deleteComputer() {
		Computer computer = new Computer();
		String choix = null;

		System.out.println("******************* Entrez l'id du pc à Suprimer *******************");
		computer = seizureVerification.saisirComputerATrouver();
		System.out.println("Voulez vous vraiment effacer --> " + computer);
		choix = seizureVerification.choixBinaire();
		if (choix.equals("1")) {
			computerService.delete(computer.getId());
		} else {
			System.out.println("OK");
		}
	}

}
