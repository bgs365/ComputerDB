package com.excilys.cdb.ui;

import java.util.Scanner;

/**
 * Menu class.
 *
 * @author sanogo
 *
 */
public class MainMenu {
  private static Scanner sc;

  /**
   * Display the principal menu.
   */
  public static void display() {
    boolean quitter = true;
    while (quitter) {
      System.out.println("******************* BIENVENUE DANS LA COMPUTER DATA BASE *******************");
      System.out.println("*******************         MENU PRINCIPAL ");
      System.out.println("******************* 1 -- ACCES- LIST COMPANY ");
      System.out.println("******************* 2 -- DETAIL OF COMPUTER ");
      System.out.println("******************* 3 -- CREATE COMPUTER ");
      System.out.println("******************* 4 -- UPDATE COMPUTER ");
      System.out.println("******************* 5 -- DELETE COMPUTER ");
      System.out.println("******************* 6 -- EXIT ");
      sc = new Scanner(System.in);
      int choix = sc.nextInt();
      System.out.println(choix);
      switch (choix) {
      case 1:
        CompanyUI.listCompany();
        break;

      case 2:
        ComputerUI.listComputer();
        break;

      case 3:
        ComputerUI.createComputer();
        break;

      case 4:
        ComputerUI.updateComputer();
        break;

      case 5:
        ComputerUI.deleteComputer();
        break;

      case 6:
        quitter = false;
        break;
      }
    }
    System.out.println("******************* Bye *******************");

  }

}
