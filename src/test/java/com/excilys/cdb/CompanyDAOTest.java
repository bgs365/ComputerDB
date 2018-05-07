package com.excilys.cdb;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import java.util.List;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.excilys.cdb.dao.CompanyDAO;
import com.excilys.cdb.model.Company;

/**
 * Class test Company persistance.
 *
 * @author sanogo
 *
 */
public class CompanyDAOTest {

  static final Logger LOGGER = LoggerFactory.getLogger(ComputerDAOTest.class);

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
    List<Company> companies = CompanyDAO.INSTANCE.findAll();
    assertEquals(42, companies.size());
    assertEquals("Apple Inc.", companies.get(0).getName());
    assertEquals("Samsung Electronics", companies.get(companies.size() - 1).getName());
  }

  /**
   * Test find all method.
   */
  @Test
  public void testFindById() {
    Company company = CompanyDAO.INSTANCE.findById(1).get();
    assertFalse(company.equals(null));
    assertEquals("Apple Inc.", company.getName());
    company = CompanyDAO.INSTANCE.findById(50).get();
    assertFalse(company.getId() != 0);
  }

  /**
   * Test find by name method.
   */
  @Test
  public void testFindByName() {
    List<Company> companies = CompanyDAO.INSTANCE.findByName("Samsung Electronics");
    assertFalse(companies.equals(null));
    assertEquals("Samsung Electronics", companies.get(0).getName());
    companies = CompanyDAO.INSTANCE.findByName("Ce nom n'existe pas");
    assertFalse(companies.size() != 0);
    companies = CompanyDAO.INSTANCE.findByName("Sun");
    assertEquals(2, companies.size());
  }

  /**
   * Test find Limit Number Of Result method.
   */
  @Test
  public void findLimitNumberOfResult() {
    List<Company> companies = CompanyDAO.INSTANCE.findLimitNumberOfResult(0, 10);
    assertEquals(10, companies.size());
    assertEquals("Apple Inc.", companies.get(0).getName());
    assertEquals("Digital Equipment Corporation", companies.get(9).getName());
    companies = CompanyDAO.INSTANCE.findLimitNumberOfResult(38, 10);
    assertEquals(4, companies.size());
    assertEquals("Texas Instruments", companies.get(0).getName());
    assertEquals("Samsung Electronics", companies.get(3).getName());
  }

}
