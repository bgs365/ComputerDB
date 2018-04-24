package com.excilys.cdb;

import static org.junit.Assert.assertEquals;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.excilys.cdb.model.Computer;
import com.excilys.cdb.service.ComputerService;
import com.ibatis.common.jdbc.ScriptRunner;


public class ComputerDAOTest {

	final static Logger logger = LoggerFactory.getLogger(ComputerDAOTest.class);

	static String driver = "com.mysql.jdbc.Driver";
	static String dataBase = "jdbc:mysql://localhost:3306/computer-database-db-test?autoReconnect=true&useSSL=false";
	static String login = "test";
	static String password = "test";
	static String emptyComputer = "DROP TABLE computer";
	static String emptyCompany = "DROP TABLE company";
	static String aSQLScriptFilePath = System.getProperty("user.dir") + "/src/test/sql/3-ENTRIES.sql";
	static String aSQLScriptFilePath2 = System.getProperty("user.dir") + "/src/test/sql/1-SCHEMA.sql";

	@Before
	public void init() throws SQLException, ClassNotFoundException, IOException {
		Class.forName(driver);
		// initialize database
		initdata();
	}

	@After
	public void destroy() {
		try (Connection connection = getConnection(); Statement statement = connection.createStatement();) {
			connection.createStatement().executeUpdate(emptyComputer);
			connection.createStatement().executeUpdate(emptyCompany);
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private void initdata() {
		// Create MySql Connection
		// Connection con;
		try (Connection connection = getConnection(); Statement statement = null) {
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
	 * Create a connection
	 * 
	 * @return connection object
	 * @throws SQLException
	 */
	private static Connection getConnection() throws SQLException {
		return DriverManager.getConnection(dataBase, login, password);
	}

	/**
	 * Get total records in table
	 * 
	 * @return total number of records. In case of exception 0 is returned
	 */
	private int getTotalRecords() {
		try (Connection connection = getConnection(); Statement statement = connection.createStatement();) {
			ResultSet result = statement.executeQuery("SELECT count(*) as total FROM computer");
			if (result.next()) {
				return result.getInt("total");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}

	@Test
	public void getTotalRecordsTest() {
		assertEquals(574, getTotalRecords());
	}

	@Test
	public void checkNameExistsTest() {
		try (Connection connection = getConnection();
				Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
						ResultSet.CONCUR_READ_ONLY);) {

			ResultSet result = statement.executeQuery("SELECT name FROM computer");

			if (result.first()) {
				assertEquals("MacBook Pro 15.4 inch", result.getString("name") );
			}
			
			if (result.last()) {
				assertEquals("iPhone 4S", result.getString("name") );
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testFindAll(){
		List<Computer> computers = ComputerService.INSTANCE.findAll();
		assertEquals(574,computers.size());
	}

}