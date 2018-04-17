package com.excilys.cdb.ui;

import java.util.Scanner;

public class MenuPricipal {
	public static void menuPrincipal() {
		boolean quitter = false;
		do{
			System.out.println("******************* BIENVENUE DANS LA COMPUTER DATA BASE *******************");
			System.out.println("******************* 		  MENU PRINCIPAL            ");
			System.out.println("******************* 			1 -- ACCES- LIST COMPANY 			 ");
		    System.out.println("******************* 			2 -- DETAIL OF COMPUTER 			 ");
			System.out.println("******************* 			3 -- CREATE COMPUTER 			    ");
			System.out.println("******************* 			4 -- UPDATE COMPUTER 			     ");
			System.out.println("******************* 			5 -- DELETE COMPUTER 			     ");
			System.out.println("******************* 			6 -- EXIT			     ");
			Scanner sc = new Scanner(System.in);
			int choix = sc.nextInt();
			System.out.println(choix);
			switch(choix) {
				case 1:quitter=false;
						
				case 6:quitter=true;
			}
		}while(quitter == false);
		

	}
}
