package com.excilys.cdb.testUnitaire;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.Reader;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.excilys.cdb.dao.Connexion;
import com.ibatis.common.jdbc.ScriptRunner;

public enum TransactionsOnTestData {

  INSTANCE;

  static final Logger LOGGER = LoggerFactory.getLogger(ComputerDAOTest.class);
  static String emptyComputer = "DROP TABLE computer";
  static String emptyCompany = "DROP TABLE company";
  static String aSQLScriptFilePath = System.getProperty("user.dir") + "/src/test/sql/3-ENTRIES.sql";
  static String aSQLScriptFilePath2 = System.getProperty("user.dir") + "/src/test/sql/1-SCHEMA.sql";

  /**
   * initialise test data.
   */
  protected void initData() {
    // Create MySql Connection
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
      LOGGER.info("Failed to Execute" + aSQLScriptFilePath + " The error is " + e.getMessage());
    }
  }

  /**
   * Destroy data.
   */
  protected void destroyDate() {
    try (Connection connection = Connexion.INSTANCE.getConnexion();
        Statement statement = connection.createStatement();) {
      connection.createStatement().executeUpdate(emptyComputer);
      connection.createStatement().executeUpdate(emptyCompany);
      connection.close();
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

}
