package com.excilys.cdb.testUnitaire;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import java.util.List;
import java.util.NoSuchElementException;

import javax.transaction.Transactional;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.excilys.cdb.dao.CompanyDao;
import com.excilys.cdb.dao.ComputerDao;
import com.excilys.cdb.model.Company;
import com.excilys.cdb.springConfig.TestConfig;

/**
 * Class test Company persistance.
 *
 * @author sanogo
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TestConfig.class)
@Configuration
public class CompanyDAOTest {

	static final Logger LOGGER = LoggerFactory.getLogger(CompanyDAOTest.class);
	@Autowired
	private CompanyDao companyDao;
	@Autowired
	private ComputerDao computerDao;

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
		List<Company> companies = companyDao.findAll();
		assertEquals(42, companies.size());
		assertEquals("Apple Inc.", companies.get(0).getName());
		assertEquals("Samsung Electronics", companies.get(companies.size() - 1).getName());
	}

	/**
	 * Test find all method.
	 */
	@Test
	public void testFindById() {
		Company company = companyDao.findById(1l).get();
		assertFalse(company.equals(null));
		assertEquals("Apple Inc.", company.getName());
		
	}
	
	/**
	 * test find by id with exception.
	 * 
	 **/
	@Test(expected = NoSuchElementException.class)
	public void testFindByIdExceptionCase() throws NoSuchElementException {
		companyDao.findById(50l).get();
	}

	/**
	 * Test find by name method.
	 */
	@Test
	public void testFindByName() {
		List<Company> companies = companyDao.findByNameContaining("Samsung Electronics");
		assertFalse(companies.equals(null));
		assertEquals("Samsung Electronics", companies.get(0).getName());
		companies = companyDao.findByNameContaining("Ce nom n'existe pas");
		assertFalse(companies.size() != 0);
		companies = companyDao.findByNameContaining("Sun");
		assertEquals(2, companies.size());
	}


	/**
	 * Test find Limit Number Of Result method.
	 */
	@Test
	public void findLimitNumberOfResult() {
		Pageable pageable = new PageRequest(0, 10);
		List<Company> companies = companyDao.findAll(pageable).getContent();
		LOGGER.debug(companies+"");
		assertEquals(10, companies.size());
		assertEquals("Apple Inc.", companies.get(0).getName());
		assertEquals("Digital Equipment Corporation", companies.get(9).getName());
		pageable = new PageRequest(4, 10);
		companies = companyDao.findAll(pageable).getContent();
		LOGGER.debug(companies+"");
		assertEquals(2, companies.size());
		assertEquals("Research In Motion", companies.get(0).getName());
		assertEquals("Samsung Electronics", companies.get(1).getName());
	}


}
