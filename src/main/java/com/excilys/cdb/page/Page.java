package com.excilys.cdb.page;

import java.util.List;

public class Page<T> {
	private List<T> objetT;
	private int indexFirstPageElement;
	private int numerosPage;
	private int nombreElementParPage;

	public Page(List<T> t, int nombreElementParPage) {
		this.numerosPage = 1;
		this.nombreElementParPage = nombreElementParPage;
		this.indexFirstPageElement = 0;
		this.setObjetT(t);
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

	public int nextPage() {
		numerosPage++;
		return indexFirstPageElement += nombreElementParPage;

	}

	public int previousPage() {
		int indexRetour = indexFirstPageElement;
		if (numerosPage > 1) {
			numerosPage--;
			indexRetour = indexFirstPageElement -= nombreElementParPage;

		} else {
			System.out.println("Operation impossible, vous êtes à la première page");
		}
		return indexRetour;
	}

	public List<T> getObjetT() {
		return objetT;
	}

	public void setObjetT(List<T> objetT) {
		this.objetT = objetT;
	}

}
