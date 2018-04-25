package com.excilys.cdb.page;

import java.util.List;

/**
 * Generic Page generator.
 *
 * @author sanogo
 *
 * @param <T>
 */
public class Page<T> {
	private List<T> objetT;
	private int indexFirstPageElement;
	private int numerosPage;
	private int nombreElementParPage;

	/**
	 * Page constructor.
	 * 
	 * @param t
	 * @param nombreElementParPage
	 */
	public Page(List<T> t, int nombreElementParPage) {
		this.numerosPage = 1;
		this.nombreElementParPage = nombreElementParPage;
		this.indexFirstPageElement = 0;
		this.setObjetT(t);
	}
	
	/**
	 *nombreElementParPage getter. 
	 *
	 * @return nombreElementParPage
	 */
	public int getNombreElementParPage() {
		return nombreElementParPage;
	}

	/**
	 * nombreElementParPage setter.
	 *
	 * @param nombreElementParPage
	 */
	public void setNombreElementParPage(int nombreElementParPage) {
		this.nombreElementParPage = nombreElementParPage;
	}
	
	/**
	 *indexFirstPageElement getter.
	 *
	 * @return indexFirstPageElement
	 */
	public int getIndexFirstPageElement() {
		return indexFirstPageElement;
	}
	/**
	 *indexFirstPageElement setter.
	 *
	 * @param indexFirstPageElement
	 */
	public void setIndexFirstPageElement(int indexFirstPageElement) {
		this.indexFirstPageElement = indexFirstPageElement;
	}
	
	/**
	 *numerosPage getter.
	 *
	 * @return
	 */
	public int getNumerosPage() {
		return numerosPage;
	}
	
	/**
	 *numerosPage setter.
	 *
	 * @param numerosPage
	 */
	public void setNumerosPage(int numerosPage) {
		this.numerosPage = numerosPage;
	}
	
	/**
	 *allow acces to next page.
	 *
	 * @return indexFirstPageElement += nombreElementParPage
	 */
	public int nextPage() {
		numerosPage++;
		return indexFirstPageElement += nombreElementParPage;

	}
	
	/**
	 *allow access to previous page.
	 *
	 * @return indexRetour
	 */
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
	
	/**
	 *objetT getter.
	 *
	 * @return objetT
	 */
	public List<T> getObjetT() {
		return objetT;
	}
	
	/**
	 *objetT setter.
	 *
	 * @param objetT
	 */
	public void setObjetT(List<T> objetT) {
		this.objetT = objetT;
	}

}
