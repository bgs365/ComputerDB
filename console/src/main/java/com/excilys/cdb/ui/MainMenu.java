package com.excilys.cdb.ui;

import java.io.IOException;
import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * Menu class.
 *
 * @author sanogo
 *
 */
public class MainMenu {
	
	public static void main(String argc[]) {	
		display();
	}
	
	static CompanyUI companyUI = new CompanyUI();
	static ComputerUI computerUI = new ComputerUI();
  static final Logger LOGGER = LoggerFactory.getLogger(MainMenu.class);
	
  private static Scanner scMainMenu;
  enum MainMenuChoice{
  	LIST_COMPANY,DETAIL_OF_COMPUTER,CREATE_COMPUTER,UPDATE_COMPUTER,DELETE_COMPUTER,DELETE_COMPANY,EXIT
  }
  
  /**
   * Display the principal menu.
   */
  public static void display() {
  	MainMenuChoice mainMenuChoice ;
  	
    boolean quitter = true;
    while (quitter) {
      System.out.println("******************* BIENVENUE DANS LA COMPUTER DATA BASE *******************");
      System.out.println("*******************      MENU PRINCIPAL ");
      System.out.println("******************* 1 -- ACCES  LIST COMPANY ");
      System.out.println("******************* 2 -- DETAIL OF COMPUTER ");
      System.out.println("******************* 3 -- CREATE COMPUTER ");
      System.out.println("******************* 4 -- UPDATE COMPUTER ");
      System.out.println("******************* 5 -- DELETE COMPUTER ");
      System.out.println("******************* 6 -- DELETE COMPANY ");
      System.out.println("******************* 7 -- EXIT ");
      scMainMenu = new Scanner(System.in);
      int choix = scMainMenu.nextInt();
      mainMenuChoice = setChoice(choix);
      System.out.println(choix);
      switch (mainMenuChoice) {
      case LIST_COMPANY:
        companyUI.listCompany();
        break;

      case DETAIL_OF_COMPUTER:
					try {
						computerUI.listComputer();
					} catch (IOException e) {
						LOGGER.info(e.getMessage());;
					}
        break;

      case CREATE_COMPUTER:
        computerUI.createComputer();
        break;

      case UPDATE_COMPUTER:
        computerUI.updateComputer();
        break;

      case DELETE_COMPUTER:
      	computerUI.deleteComputer();
        break;
        
      case DELETE_COMPANY:
        companyUI.deleteCompany();
        break;

      case EXIT:
        quitter = false;
        break;
      }
    }
    System.out.println("******************* Bye *******************");

  }
  
	private static MainMenuChoice setChoice(int enterChoice) {
  	MainMenuChoice mainMenuChoice = null;
  	if(enterChoice==1) {
    	mainMenuChoice = MainMenuChoice.LIST_COMPANY;
    }else if(enterChoice==2) {
    	mainMenuChoice = MainMenuChoice.DETAIL_OF_COMPUTER;
    }else if(enterChoice==3) {
    	mainMenuChoice = MainMenuChoice.CREATE_COMPUTER;
    }else if(enterChoice==4) {
    	mainMenuChoice = MainMenuChoice.UPDATE_COMPUTER;
    }else if(enterChoice==5) {
    	mainMenuChoice = MainMenuChoice.DELETE_COMPUTER;
    }else if(enterChoice==6) {
    	mainMenuChoice = MainMenuChoice.DELETE_COMPANY;
    }else if(enterChoice==7) {
    	mainMenuChoice = MainMenuChoice.EXIT;
    }
  	return mainMenuChoice;
  }
  

}
