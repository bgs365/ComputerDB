package com.excilys.cdb.ui;

import java.util.List;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.excilys.cdb.model.Company;
import com.excilys.cdb.page.Page;
import com.excilys.cdb.service.CompanyService;
import com.excilys.cdb.service.ComputerService;
import com.excilys.cdb.springConfig.ApplicationConfig;

public class CompanyUI {
	
	static ApplicationContext context = new AnnotationConfigApplicationContext(ApplicationConfig.class);
	static CompanyService companyService = (CompanyService) context.getBean(CompanyService.class);
	static ComputerService computerService = (ComputerService) context.getBean(ComputerService.class);
  private static SeizureVerification seizureVerification;
  
  private static Scanner sc = new Scanner(System.in);
  private static int nombrElementParPage = 10;

  /**
   * display a page of company, and allow to navigate between them.
   */
  public  void listCompany() {
    List<Company> companies = companyService.findLimitNumberOfResult(0, nombrElementParPage);
    Page<Company> companyPage = new Page<Company>(companies, nombrElementParPage, companyService.findAll().size());
    String choix = null;

    System.out.println("*******************Listing company *******************");
    System.out.println("Page n° " + companyPage.getNumerosPage());
    System.out.println(display(companies));
    System.out.println(" << Page precedente 1 ** 0  Quitter ** 2 Page suivante >> ");
    boolean quiter = false;
    do {
      choix = sc.nextLine();
      if (choix.equals("1") | choix.equals("2") | choix.equals("0")) {
        if (choix.equals("1") | choix.equals("2")) {
          switch (choix) {
          case "1":
            companies = companyService.findLimitNumberOfResult(companyPage.previousPage(), nombrElementParPage);
            System.out.println("Page n° " + companyPage.getNumerosPage());
            System.out.println(display(companies));
            System.out.println(" << Page precedente 1 ** 0  Quitter ** 2 Page suivante >> ");
            break;

          case "2":
            companies = companyService.findLimitNumberOfResult(companyPage.nextPage(), nombrElementParPage);
            System.out.println("Page n° " + companyPage.getNumerosPage());
            System.out.println(display(companies));
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

  }
  
  /**
   * delete company.
   */
 public void deleteCompany() {
   Company company = new Company();
   String choix = null;

   System.out.println("******************* Entrez company id to delete it *******************");
   company = seizureVerification.saisirCompanyATrouver();
   System.out.println("VAre you sure to delete this company ? --> " + company);
   choix = SeizureVerification.choixBinaire();
   if (choix.equals("1")) {
     companyService.delete(company.getId());
   } else {
     System.out.println("OK");
   }
 }

  /**
   * take a group of company and rage them in a string.
   *
   * @param company
   *          asName
   * @return String
   */
  private static String display(List<Company> company) {
    String valeurRetour = "";
    for (Company indexOfCompany : company) {
      valeurRetour += "------>" + indexOfCompany.toString() + "\n";
    }
    return valeurRetour;
  }

}
