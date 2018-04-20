package com.excilys.cdb.ui;

import java.util.List;
import java.util.Scanner;

import com.excilys.cdb.model.Company;
import com.excilys.cdb.page.CompanyPage;
import com.excilys.cdb.page.Page;
import com.excilys.cdb.service.CompanyService;

public class UICompany {

	private static Scanner sc = new Scanner(System.in);
	
	public static void listCompany() {
		List<Company> companies = CompanyService.INSTANCE.findLimitNumberOfResult(0, 10);
		Page<Company> companyPage = new Page(companies);
		String choix = null;
		
		System.out.println("*******************Listing company *******************");
		System.out.println(companyPage.toString());
		System.out.println(" << Page precedente 1 ** 0  Quitter ** 2 Page suivante >> ");
		boolean quiter = false;
		do{
			choix = sc.nextLine();
			if( choix.equals("1") | choix.equals("2") | choix.equals("0")) {
				if(choix.equals("1") | choix.equals("2")){
					System.out.println(companyPage.toString());
					System.out.println(" << Page precedente 1 ** 0  Quitter ** 2 Page suivante >> ");
					switch(choix) {
						case "1":
							companyPage.previousPage();
						break;
						
						case "2":
							companyPage.nextPage();
						break;
					}
					quiter = false;
				}else {
					quiter = true;
				}
			}else {
				System.out.println("Choix non valide");
				quiter = false;
			}
			
		}while(!quiter) ;
		
	}
	
	public static void main(String[] args) {
		listCompany();
	}

}
