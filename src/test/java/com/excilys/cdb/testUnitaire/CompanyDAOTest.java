package com.excilys.cdb.testUnitaire;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.excilys.cdb.dao.CompanyDAO;
import com.excilys.cdb.dao.ComputerDAO;
import com.excilys.cdb.model.Company;

/**
 * Class test Company persistance.
 *
 * @author sanogo
 *
 */
public class CompanyDAOTest {

  static final Logger LOGGER = LoggerFactory.getLogger(CompanyDAOTest.class);
  @Autowired
  private CompanyDAO companyDAO;
  @Autowired
  private ComputerDAO computerDAO;

  /**
   * init test data.
   */
  @Before
  public void init() {
    // initialize database
    TransactionsOnTestData.INSTANCE.initData();
  }

  /**
   * destroy test data.
   */
  @After
  public void destroy() {
    TransactionsOnTestData.INSTANCE.destroyDate();
  }

  /**
   * Test find all method.
   */
  @Test
  public void testFindAll() {
    List<Company> companies = companyDAO.findAll();
    assertEquals(42, companies.size());
    assertEquals("Apple Inc.", companies.get(0).getName());
    assertEquals("Samsung Electronics", companies.get(companies.size() - 1).getName());
  }

  /**
   * Test find all method.
   */
  @Test
  public void testFindById() {
    Company company = companyDAO.findById(1).get();
    assertFalse(company.equals(null));
    assertEquals("Apple Inc.", company.getName());
    company = companyDAO.findById(50).get();
    assertFalse(company.getId() != 0);
  }

  /**
   * Test find by name method.
   */
  @Test
  public void testFindByName() {
    List<Company> companies = companyDAO.findByName("Samsung Electronics");
    assertFalse(companies.equals(null));
    assertEquals("Samsung Electronics", companies.get(0).getName());
    companies = companyDAO.findByName("Ce nom n'existe pas");
    assertFalse(companies.size() != 0);
    companies = companyDAO.findByName("Sun");
    assertEquals(2, companies.size());
  }

  /**
   * Test find Limit Number Of Result method.
   */
  @Test
  public void findLimitNumberOfResult() {
    List<Company> companies = companyDAO.findLimitNumberOfResult(0, 10);
    assertEquals(10, companies.size());
    assertEquals("Apple Inc.", companies.get(0).getName());
    assertEquals("Digital Equipment Corporation", companies.get(9).getName());
    companies = companyDAO.findLimitNumberOfResult(38, 10);
    assertEquals(4, companies.size());
    assertEquals("Texas Instruments", companies.get(0).getName());
    assertEquals("Samsung Electronics", companies.get(3).getName());
  }
  
  /**
   * Test delete company.
   */
  @Test
  public void testdelete() {
  	assertEquals(1, companyDAO.delete(1));
    assertEquals(0, companyDAO.findById(1).get().getId());
    assertEquals(null, computerDAO.findById(6).get().getName());
    assertEquals(0, companyDAO.delete(1));
  }

}
