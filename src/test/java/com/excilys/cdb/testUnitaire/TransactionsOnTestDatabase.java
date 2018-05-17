package com.excilys.cdb.testUnitaire;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.Reader;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.excilys.cdb.springConfig.ApplicationConfig;
import com.ibatis.common.jdbc.ScriptRunner;

public class TransactionsOnTestDatabase {

  static final Logger LOGGER = LoggerFactory.getLogger(TransactionsOnTestDatabase.class);
  static ApplicationContext context = new AnnotationConfigApplicationContext(ApplicationConfig.class);
	static DataSource dataSource = (DataSource) context.getBean(DataSource.class);
  static String emptyComputer = "DROP TABLE computer";
  static String emptyCompany = "DROP TABLE company";

  static String aSQLScriptFilePath = System.getProperty("user.dir") + "/src/test/sql/1-SCHEMA.sql";
  static String aSQLScriptFilePath2 = System.getProperty("user.dir") + "/src/test/sql/3-ENTRIES.sql";

  /**
   * initialise test data.
   */
  public static void initData() {
    try (Connection connection = dataSource.getConnection(); Statement statement = null) {
    	LOGGER.debug(aSQLScriptFilePath);
    	
      ScriptRunner sr = new ScriptRunner(connection, false, false);
      Reader reader = new BufferedReader(new FileReader(aSQLScriptFilePath));
      Reader reader2 = new BufferedReader(new FileReader(aSQLScriptFilePath2));
      sr.runScript(reader);
      sr.runScript(reader2);

    } catch (Exception e) {
      LOGGER.info("Failed to Execute " + aSQLScriptFilePath + " The error is " + e.getMessage());
    }
  }

  /**
   * Destroy data.
   */
  public static void destroyDate() {
    try (Connection connection = dataSource.getConnection();
        Statement statement = connection.createStatement();) {
      connection.createStatement().executeUpdate(emptyComputer);
      connection.createStatement().executeUpdate(emptyCompany);
      connection.close();
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

}
