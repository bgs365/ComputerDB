package com.excilys.cdb.ui;

import java.util.Scanner;

public class MenuPricipal {
	private static Scanner sc;
	static UICompany uiCompany = new UICompany();
	static UIComputer uiComputer = new UIComputer();

	public static void menuPrincipal() {
		boolean quitter = true;
		while(quitter){
			System.out.println("******************* BIENVENUE DANS LA COMPUTER DATA BASE *******************");
			System.out.println("******************* 		  MENU PRINCIPAL            					");
			System.out.println("******************* 			1 -- ACCES- LIST COMPANY 			 		");
		    System.out.println("******************* 			2 -- DETAIL OF COMPUTER 					");
			System.out.println("******************* 			3 -- CREATE COMPUTER 			     		");
			System.out.println("******************* 			4 -- UPDATE COMPUTER 			     		");
			System.out.println("******************* 			5 -- DELETE COMPUTER 			     		");
			System.out.println("******************* 			6 -- EXIT			     					");
			sc = new Scanner(System.in);
			int choix = sc.nextInt();
			System.out.println(choix);
			switch(choix) {
				case 1:
					 UICompany.listCompany();
				break;
				
				case 2:
					 UIComputer.listComputer();
				break;
				
				case 3:
					 UIComputer.createComputer();
				break;
				
				case 4:
					System.out.println("Choix "+choix);;
				break;
				
				case 5:
					System.out.println("Choix "+choix);;
				break;
						
				case 6:
					quitter=false;
				break;
			}
		}
		System.out.println("******************* Bye *******************");

	}
	
}
