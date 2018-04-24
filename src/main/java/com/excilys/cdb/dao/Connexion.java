package com.excilys.cdb.dao;

import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ResourceBundle;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public enum Connexion {

	INSTANCE;
	final static Logger logger = LoggerFactory.getLogger(Connexion.class);
	
	
	static String driver = "com.mysql.jdbc.Driver";
	static String baseDeDonnee = null;
	static String login = null;
	static String motDePasse = null;

	public static void getConnexionVariables() {
		
		ResourceBundle input = ResourceBundle.getBundle("config");

		baseDeDonnee = input.getString("database");
		login = input.getString("dbuser");
		motDePasse = input.getString("dbpassword");

		
	}

	public Connection getConnexion() {

		logger.info("-------- MySQL JDBC Connection Testing ------------");
		getConnexionVariables();
		try {
			Class.forName(driver);
		} catch (ClassNotFoundException e) {
			logger.info("Where is your MySQL JDBC Driver?");
			e.printStackTrace();
		}
		logger.info("MySQL JDBC Driver Registered!");
		Connection connection = null;

		try {
			connection = DriverManager.getConnection(baseDeDonnee, login, motDePasse);
		} catch (SQLException e) {
			logger.info("Connection Failed! Check output console");
			e.printStackTrace();
		}

		return connection;
	}

}