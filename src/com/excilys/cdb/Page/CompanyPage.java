package com.excilys.cdb.Page;

import java.util.List;

import com.excilys.cdb.model.Company;
import com.excilys.cdb.service.CompanyService;

public class CompanyPage {
	private List<Company> companies;
	private CompanyService companyService;
	private int nombreElementParPage;
	private int indexFirstPageElement;
	private int numerosPage;
	

	public CompanyPage(){
		this.numerosPage = 1;
		this.nombreElementParPage = 10;
		this.indexFirstPageElement = 0;
		companyService = new CompanyService();
		companies = companyService.findLimitNumberOfResult(indexFirstPageElement, nombreElementParPage);
		
	}

	public int getNombreElementParPage() {
		return nombreElementParPage;
	}

	public void setNombreElementParPage(int nombreElementParPage) {
		this.nombreElementParPage = nombreElementParPage;
	}
	
	public int getNumerosPage() {
		return numerosPage;
	}

	public void setNumerosPage(int numerosPage) {
		this.numerosPage = numerosPage;
	}

	public int getIndexFirstPageElement() {
		return indexFirstPageElement;
	}

	public void setIndexFirstPageElement(int indexFirstPageElement) {
		this.indexFirstPageElement = indexFirstPageElement;
	}
	
	public void nextPage() {
			numerosPage++;
			indexFirstPageElement+=nombreElementParPage;
			companies = companyService.findLimitNumberOfResult(indexFirstPageElement, nombreElementParPage);
	}
	
	public void previousPage() {
		if(numerosPage > 1) {
			numerosPage--;
			indexFirstPageElement-=nombreElementParPage;
			companies = companyService.findLimitNumberOfResult(indexFirstPageElement, nombreElementParPage);
		}else{
			System.out.println("Operation impossible, vous êtes à la première page");
		}
	}

	@Override
	public String toString() {
		String valeurRetour = "Page n° "+(getNumerosPage())+"\n";
		for(Company cpn : companies) {
			valeurRetour += "------>" + cpn.getName()+"\n";
		}
		
		return valeurRetour;
	}
	
}
