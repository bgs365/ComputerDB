package com.excilys.cdb.ui;

import java.time.LocalDate;
import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.data.domain.Page;

import com.excilys.cdb.exceptions.CdbException;
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
	private static SeizureVerification seizureVerification;
	static ApplicationContext context = new AnnotationConfigApplicationContext(ApplicationConfig.class);
	static CompanyService companyService = (CompanyService) context.getBean(CompanyService.class);
	static ComputerService computerService = (ComputerService) context.getBean(ComputerService.class);
  private Scanner sc = new Scanner(System.in);
  private int nombrElementParPage = 50;
  static final Logger LOGGER = LoggerFactory.getLogger(ComputerUI.class);

  /**
   * display a page of computer, allow to navigate between pages of computers.
   */
  public void listComputer() {

    Page<Computer> computerPage = computerService.findByComputerAndCompanyNameLimit("", 0, nombrElementParPage);
    String choix = "";

    System.out.println("******************* Liste of Computers *******************");
    System.out.println("Page n° " + computerPage.getNumber());
    System.out.println(display(computerPage));
    System.out.println(" << Page precedente 1 ** 0  Quitter ** 2 Page suivante >> ");
    boolean quiter = false;
    do {
      choix = sc.nextLine();
      if (choix.equals("1") | choix.equals("2") | choix.equals("0")) {
        if (choix.equals("1") | choix.equals("2")) {
          switch (choix) {
          case "1":
            computerPage.nextPageable();
            System.out.println("Page n° " + computerPage.getNumber());
            System.out.println(display(computerPage));
            System.out.println(" << Page precedente 1 ** 0  Quitter ** 2 Page suivante >> ");
            break;

          case "2":
          	 computerPage.previousPageable();
             System.out.println("Page n° " + computerPage.getNumber());
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

    for (Computer cpt : computerPage.getContent()) {
      System.out.println("<-- " + cpt.getName() + " --> introduced = " + cpt.getIntroduced() + " | discontinued = "
          + cpt.getDiscontinued() + " | " + " -->");
    }
    System.out.println("******************* " + computerPage.getNumber() + " PC *******************");

  }

  /**
   * take a group of computers and range them in a string.
   *
   * @param computerPage
   *          List<Computer>
   * @return String
   */
  private static String display(Page<Computer> computerPage) {
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
    nom = SeizureVerification.saisieNom();
    System.out.println("--> Nom = " + nom);

    System.out.println("--> Entrez la date d'introduction : ");
    introducedDate = SeizureVerification.saisieDate();
    System.out.println("--> Date d'intro = " + introducedDate);

    System.out.println("--> Entrez la date de retait : ");
    discontinuedDate = SeizureVerification.saisieDate();
    System.out.println("--> Date de retrait = " + discontinuedDate);

    System.out.println("--> Entrez le nom de la company : ");
    //company = seizureVerification.saisirCompany();
    System.out.println("--> Company = " + company);

    computer.setName(nom);
    computer.setIntroduced(introducedDate);
    computer.setDiscontinued(discontinuedDate);
    //computer.setCompany(company);

    try {
      computerService.save(computer);
    } catch (CdbException e) {
      LOGGER.info(e.getMessage());
    }

  }

  /**
   * update computer menu.
   */
  public void updateComputer() {
    Computer computer = new Computer();
    String nom = null;
    LocalDate introducedDate = null;
    LocalDate discontinuedDate = null;
    Company company = null;
    String choix = null;

    System.out.println("******************* Entrez l'id du pc à Modifier *******************");
    computer = seizureVerification.saisirComputerATrouver();
    System.out.println("Le PC choisi est --> " + computer);

    if (computer != null) {
      System.out.println("--> Voulez vous modifier le nom ?");
      choix = SeizureVerification.choixBinaire();
      if (choix.equals("1")) {
        System.out.println("--> Entrez le nom du PC : ");
        nom = SeizureVerification.saisieNom();
        System.out.println("--> Nom = " + nom);
        computer.setName(nom);
      }

      System.out.println("--> Voulez vous modifier la date d'introduction ?");
      choix = SeizureVerification.choixBinaire();
      if (choix.equals("1")) {
        System.out.println("--> Entrez la date d'introduction : ");
        introducedDate = SeizureVerification.saisieDate();
        System.out.println("--> Date d'intro = " + introducedDate);
        computer.setIntroduced(introducedDate);
      }

      System.out.println("--> Voulez vous modifier la date de retait ?");
      choix = SeizureVerification.choixBinaire();
      if (choix.equals("1")) {
        System.out.println("--> Entrez la date de retait : ");
        discontinuedDate = SeizureVerification.saisieDate();
        System.out.println("--> Date de retrait = " + discontinuedDate);
        computer.setDiscontinued(discontinuedDate);
      }

      System.out.println("--> Voulez vous modifier le nom de company ?");
      choix = SeizureVerification.choixBinaire();
      if (choix.equals("1")) {
        System.out.println("--> Entrez le nom de la company : ");
        company = seizureVerification.saisirCompany();
        System.out.println("--> Company = " + company);

        try {
          computerService.update(computer);
        } catch (CdbException e) {
          e.printStackTrace();
        }
      }

      computer.setCompany(company);
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
    choix = SeizureVerification.choixBinaire();
    if (choix.equals("1")) {
      computerService.delete(computer.getId());
    } else {
      System.out.println("OK");
    }
  }

}
