package com.excilys.cdb;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.Reader;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.excilys.cdb.dao.ComputerDAO;
import com.excilys.cdb.dao.Connexion;
import com.excilys.cdb.model.Computer;
import com.excilys.cdb.service.CompanyService;
import com.excilys.cdb.service.ComputerService;
import com.ibatis.common.jdbc.ScriptRunner;

/**
 * Test co
 * @author sanogo
 *
 */
public class ComputerDAOTest {

	final static Logger logger = LoggerFactory.getLogger(ComputerDAOTest.class);

	static String emptyComputer = "DROP TABLE computer";
	static String emptyCompany = "DROP TABLE company";
	static String aSQLScriptFilePath = System.getProperty("user.dir") + "/src/test/sql/3-ENTRIES.sql";
	static String aSQLScriptFilePath2 = System.getProperty("user.dir") + "/src/test/sql/1-SCHEMA.sql";
	
	List<Computer> computers;
	Computer computer;
	
	/**
	 * init test data
	 */
	@Before
	public void init() {
		// initialize database
		initdata();
	}
	
	/**
	 * destroy test data
	 */
	@After
	public void destroy() {
		try (Connection connection = Connexion.INSTANCE.getConnexion(); Statement statement = connection.createStatement();) {
			connection.createStatement().executeUpdate(emptyComputer);
			connection.createStatement().executeUpdate(emptyCompany);
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * initialise test data
	 */
	private void initdata() {
		// Create MySql Connection
		// Connection con;
		try (Connection connection = Connexion.INSTANCE.getConnexion(); Statement statement = null) {
			// Initialize object for ScripRunner
			ScriptRunner sr = new ScriptRunner(connection, false, false);
			// Give the input file to Reader
			Reader reader = new BufferedReader(new FileReader(aSQLScriptFilePath));
			Reader reader2 = new BufferedReader(new FileReader(aSQLScriptFilePath2));
			// Exctute script
			sr.runScript(reader2);
			sr.runScript(reader);

		} catch (Exception e) {
			logger.info("Failed to Execute" + aSQLScriptFilePath + " The error is " + e.getMessage());
		}

	}

	/**
	 * Test find all method
	 */
	@Test
	public void testFindAll(){
		List<Computer> computers = ComputerDAO.INSTANCE.findAll();
		assertEquals(574,computers.size());
		assertEquals("MacBook Pro 15.4 inch",computers.get(0).getName() );
		assertEquals("iPhone 4S",computers.get(computers.size()-1).getName());
	}
	
	/**
	 * Test find by id method
	 */
	@Test
	public void testFindbyId(){
		computer = ComputerDAO.INSTANCE.findById(16);
		assertEquals("Apple II",computer.getName() );
		assertEquals(LocalDate.parse("1977-04-01"),computer.getIntroduced() );
		assertEquals(LocalDate.parse("1993-10-01"),computer.getDiscontinued() );
		assertEquals(1,computer.getCompany().getId());
	}
	
	/**
	 * Test find by name method
	 */
	@Test
	public void testFindbyName(){
		computers = ComputerDAO.INSTANCE.findByName("Apple II");
		assertEquals(1,computers.size() );
		assertEquals(16,computers.get(0).getId() );
		assertEquals(LocalDate.parse("1977-04-01"),computers.get(0).getIntroduced() );
		assertEquals(LocalDate.parse("1993-10-01"),computers.get(0).getDiscontinued() );
		assertEquals(1,computers.get(0).getCompany().getId());
	}
	
	/**
	 * Test find by company 
	 */
	@Test
	public void testFindbyCompany(){
		computers = ComputerDAO.INSTANCE.findByCompany(1);
		/*Le test est fait sur le 2e index de la requete suivante
		 * SELECT * FROM computer LEFT JOIN company ON company.id = computer.company_id WHERE computer.company_id = ?"
		 */
		assertEquals(39,computers.size() );
		assertEquals(6,computers.get(1).getId() );
		assertEquals(LocalDate.parse("2006-01-10") ,computers.get(1).getIntroduced() );
		assertEquals(null,computers.get(1).getDiscontinued() );
		assertEquals("MacBook Pro",computers.get(1).getName());
	}
	
	/**
	 * Test save computer 
	 */
	@Test
	public void testSave(){
		 	computer = new Computer();
		 	computer.setId(575);
		 	computer.setName("Test save computer");
		 	computer.setIntroduced(LocalDate.parse("2006-01-10"));
		 	computer.setDiscontinued(LocalDate.parse("2012-01-10"));
		 	computer.setCompany(CompanyService.INSTANCE.findById(1));
		 	ComputerDAO.INSTANCE.save(computer);
	        assertNotNull(ComputerDAO.INSTANCE.findByCompany(575));
	}
	
	/**
	 * Test update computer 
	 */
	@Test
	public void testUpdate(){
		 	computer = ComputerService.INSTANCE.findById(575);
		 	computer.setName("Test update computer");
		 	computer.setIntroduced(LocalDate.parse("2007-02-11"));
		 	computer.setDiscontinued(LocalDate.parse("2013-02-11"));
		 	computer.setCompany(CompanyService.INSTANCE.findById(2));
		 	ComputerDAO.INSTANCE.update(computer);
	        assertEquals("Test update computer",computer.getName() );
			assertEquals(LocalDate.parse("2007-02-11"),computer.getIntroduced() );
			assertEquals(LocalDate.parse("2013-02-11"),computer.getDiscontinued() );
			assertEquals(2,computer.getCompany().getId());
	}
	
	/**
	 * Test delete computer 
	 */
	@Test
	public void testdelete(){
		 	ComputerDAO.INSTANCE.delete(575);
		 	assertEquals(0, ComputerDAO.INSTANCE.findById(575).getId() );
	}

}