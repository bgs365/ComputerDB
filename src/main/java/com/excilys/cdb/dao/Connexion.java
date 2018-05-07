package com.excilys.cdb.dao;

import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ResourceBundle;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public enum Connexion {
  INSTANCE;

  static String driver = "com.mysql.jdbc.Driver";
  static String baseDeDonnee = null;
  static String login = null;
  static String motDePasse = null;
  static final Logger LOGGER = LoggerFactory.getLogger(Connexion.class);

  /**
   * load connexion variables.
   */
  public static void getConnexionVariables() {

    ResourceBundle input = ResourceBundle.getBundle("config");

    baseDeDonnee = input.getString("database");
    login = input.getString("dbuser");
    motDePasse = input.getString("dbpassword");

  }

  /**
   * make connexion to db.
   *
   * @return Connection
   */
  public Connection getConnexion() {

    getConnexionVariables();
    try {
      Class.forName(driver);
    } catch (ClassNotFoundException e) {
      LOGGER.error("Where is your MySQL JDBC Driver?", e);
    }
    Connection connection = null;

    try {
      connection = DriverManager.getConnection(baseDeDonnee, login, motDePasse);
    } catch (SQLException e) {
      LOGGER.error("Connection Failed! Check output console", e);

    }

    return connection;
  }

}