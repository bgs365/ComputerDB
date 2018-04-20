package com.excilys.cdb.page;

import java.util.List;

public class Page <T>{
	private List<T> t;
	private int indexFirstPageElement;
	private int numerosPage;
	private int nombreElementParPage;
	
	public Page(List<T> t) {
		this.numerosPage = 1;
		this.nombreElementParPage = 10;
		this.indexFirstPageElement = 0;
		this.t = t;
	}
	
	public int getNombreElementParPage() {
		return nombreElementParPage;
	}

	public void setNombreElementParPage(int nombreElementParPage) {
		this.nombreElementParPage = nombreElementParPage;
	}

	public int getIndexFirstPageElement() {
		return indexFirstPageElement;
	}

	public void setIndexFirstPageElement(int indexFirstPageElement) {
		this.indexFirstPageElement = indexFirstPageElement;
	}

	public int getNumerosPage() {
		return numerosPage;
	}

	public void setNumerosPage(int numerosPage) {
		this.numerosPage = numerosPage;
	}

	public void nextPage() {
		numerosPage++;
		indexFirstPageElement+=nombreElementParPage;
		
	}

	public void previousPage() {
		if(numerosPage > 1) {
			numerosPage--;
			indexFirstPageElement-=nombreElementParPage;
			
		}else{
			System.out.println("Operation impossible, vous êtes à la première page");
		}
	}

	@Override
	public String toString() {
		String valeurRetour = "Page n° "+(getNumerosPage())+"\n";
		for(T indexOfT : t) {
			valeurRetour += "------>" + indexOfT.toString()+"\n";
		}
		
		return valeurRetour;
	}
	
	
	
}
