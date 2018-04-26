package com.excilys.cdb.page;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Generic Page generator.
 *
 * @author sanogo
 *
 * @param <T>
 */
public class Page<T> {
  private List<T> t;
  private int indexFirstPageElement;
  private int numerosPage;
  private int nombreElementParPage;
  private int nombreElementTotal;
  static final Logger LOGGER = LoggerFactory.getLogger(Page.class);

  /**
   * Page constructor.
   *
   * @param t
   *          object
   * @param nombreElementParPage
   *          asName
   * @param nombreElementTotal
   *          asName
   */
  public Page(List<T> t, int nombreElementParPage, int nombreElementTotal) {
    this.numerosPage = 1;
    this.nombreElementParPage = nombreElementParPage;
    this.indexFirstPageElement = 0;
    this.nombreElementTotal = nombreElementTotal;
    this.setObjetT(t);
  }

  /**
   * nombreElementParPage getter.
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
   *          asName
   */
  public void setNombreElementParPage(int nombreElementParPage) {
    this.nombreElementParPage = nombreElementParPage;
  }

  /**
   * indexFirstPageElement getter.
   *
   * @return indexFirstPageElement
   */
  public int getIndexFirstPageElement() {
    return indexFirstPageElement;
  }

  /**
   * indexFirstPageElement setter.
   *
   * @param indexFirstPageElement
   *          asName
   */
  public void setIndexFirstPageElement(int indexFirstPageElement) {
    this.indexFirstPageElement = indexFirstPageElement;
  }

  public int getNumerosPage() {
    return numerosPage;
  }

  public void setNumerosPage(int numerosPage) {
    this.numerosPage = numerosPage;
  }

  /**
   * allow acces to next page.
   *
   * @return indexFirstPageElement += nombreElementParPage
   */
  public int nextPage() {
    int indexRetour = indexFirstPageElement;
    if (indexRetour < (nombreElementTotal - nombreElementParPage)) {
      numerosPage++;
      indexRetour = indexFirstPageElement += nombreElementParPage;

    } else {
      LOGGER.info("Operation impossible, vous êtes à la dernière page");
    }
    return indexRetour;

  }

  /**
   * allow access to previous page.
   *
   * @return indexRetour
   */
  public int previousPage() {
    int indexRetour = indexFirstPageElement;
    if (numerosPage > 1) {
      numerosPage--;
      indexRetour = indexFirstPageElement -= nombreElementParPage;

    } else {
      LOGGER.info("Operation impossible, vous êtes à la première page");
    }
    return indexRetour;
  }

  /**
   * objetT getter.
   *
   * @return objetT
   */
  public List<T> getObjetT() {
    return t;
  }

  public void setObjetT(List<T> objetT) {
    this.t = objetT;
  }

}