package com.excilys.cdb.testUnitaire;

import static org.junit.Assert.assertEquals;

import java.time.LocalDate;
import java.util.List;
import java.util.NoSuchElementException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.excilys.cdb.dao.CompanyDao;
import com.excilys.cdb.dao.ComputerDao;
import com.excilys.cdb.model.Company;
import com.excilys.cdb.model.Computer;
import com.excilys.cdb.springConfig.TestConfig;

/**
 * Test computer persistance.
 *
 * @author sanogo
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TestConfig.class)
@Configuration
public class ComputerDAOTest {

	@Autowired
	ComputerDao computerDao;
	@Autowired
	CompanyDao companyDao;

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
		List<Computer> computers = computerDao.findAll();
		assertEquals(574, computers.size());
		assertEquals("MacBook Pro 15.4 inch", computers.get(0).getName());
		assertEquals("iPhone 4S", computers.get(computers.size() - 1).getName());
	}

	/**
	 * Test find by id method.
	 */
	@Test
	public void testFindbyId() {
		Computer computer = computerDao.findById(1l).get();
		assertEquals("Apple II", computer.getName());
		assertEquals(LocalDate.parse("1977-04-01"), computer.getIntroduced());
		assertEquals(LocalDate.parse("1993-10-01"), computer.getDiscontinued());
		//assertEquals(1, computer.getCompany().getId() );
	}
	
	/**
	 * test find by id with exception.
	 * @throws EmptyResultDataAccessException
	 */
	@Test(expected = NoSuchElementException.class)
	public void testFindByIdExceptionCase() throws NoSuchElementException {
		computerDao.findById(1000).get();
	}

	/**
	 * Test find Limit Number Of Result by name.
	 */
	@Test
	public void findByComputerAndCompanyNameLimit() {
		Pageable pageable = new PageRequest(0, 10);
		List<Computer> computers = computerDao.findByNameContainingOrCompanyNameContaining("Apple", "Apple", pageable).getContent();
		assertEquals(10, computers.size());
		assertEquals("Apple I", computers.get(0).getName());
		assertEquals("Apple Lisa", computers.get(9).getName());
		pageable = new PageRequest(5, 10);
		computers = computerDao.findByNameContainingOrCompanyNameContaining("Apple", "Apple", pageable).getContent();
		assertEquals(6, computers.size());
		assertEquals("Macintosh SE", computers.get(0).getName());
		assertEquals("Upcoming iPhone 5", computers.get(5).getName());
		pageable = new PageRequest(1000, 10);
		computers = computerDao.findByNameContainingOrCompanyNameContaining("Apple", "Apple", pageable).getContent();
		assertEquals(0, computers.size());
	}



	/**
	 * Test save computer.
	 */
	@Test
	public void testSave() {
		Computer computer = new Computer();
		computer.setId(575);
		computer.setName("Test save computer");
		computer.setIntroduced(LocalDate.parse("2006-01-10"));
		computer.setDiscontinued(LocalDate.parse("2012-01-10"));
		computer.setCompany(companyDao.findById(1l).get());
		assertEquals(1,computerDao.save(computer));
		computer.setId(576);
		computer.setCompany(new Company(50l,"Don't Exist"));
		assertEquals(1,computerDao.save(computer));
	}

	/**
	 * Test update computer.
	 */
	@Test
	public void testUpdate() {
		Computer computer = computerDao.findById(574l).get();
		computer.setName("Test update computer");
		computer.setIntroduced(LocalDate.parse("2007-02-11"));
		computer.setDiscontinued(LocalDate.parse("2013-02-11"));
		computer.setCompany(companyDao.findById(2l).get());
		computerDao.save(computer);
		assertEquals("Test update computer", computer.getName());
		assertEquals(LocalDate.parse("2007-02-11"), computer.getIntroduced());
		assertEquals(LocalDate.parse("2013-02-11"), computer.getDiscontinued());
	//	assertEquals(2l, computer.getCompany().getId());

		computer = computerDao.findById(1).get();
		computer.setName("Test change macbook");
		computerDao.save(computer);
		assertEquals("Test change macbook", computer.getName());

		computer = computerDao.findById(2l).get();
		computer.setName("Test change 2");
		computer.setCompany(new Company(0l, null));
		assertEquals(1, computerDao.save(computer));
		assertEquals("Test change 2", computer.getName());
	}

	/**
	 * Test delete computer.
	 */
	@Test
	public void testdelete() {
		computerDao.delete(574l);
	}

}