package com.excilys.cdb.testUnitaire;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.excilys.cdb.dao.CompanyDAO;
import com.excilys.cdb.model.Company;
import com.excilys.cdb.springConfig.ApplicationConfig;

/**
 * Class test Company persistance.
 *
 * @author sanogo
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = ApplicationConfig.class)
@Configuration
public class CompanyDAOTest {

	static final Logger LOGGER = LoggerFactory.getLogger(CompanyDAOTest.class);
	@Autowired
	private CompanyDAO companyDAO;

	/**
	 * init test data.
	 */
	@Before
	public void init() {
		TransactionsOnTestDatabase.initData();
	}

	/**
	 * destroy test data.
	 */
	@After
	public void destroy() {
		TransactionsOnTestDatabase.destroyDate();
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
		
	}
	
	/**
	 * test find by id with exception.
	 * @throws EmptyResultDataAccessException
	 */
	@Test(expected = EmptyResultDataAccessException.class)
	public void testFindByIdExceptionCase() throws EmptyResultDataAccessException {
		companyDAO.findById(50).get();
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
		assertEquals(0, companyDAO.delete(1));
	}

}
