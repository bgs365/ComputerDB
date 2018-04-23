package com.excilys.cdb.dao;

import java.sql.DriverManager;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.excilys.cdb.main.Main;

public enum Connexion {

	INSTANCE;
	final static Logger logger = LoggerFactory.getLogger(Main.class);
	
	static String driver = "com.mysql.jdbc.Driver";
	static String baseDeDonnee = null;
	static String login = null;
	static String motDePasse = null;
	
	public static void getConnexionVariables() {
		Properties prop = new Properties();
		InputStream input = null;

		try {

			input = new FileInputStream("config.properties");

			// load a properties file
			prop.load(input);

			baseDeDonnee = prop.getProperty("database");
			login = prop.getProperty("dbuser");
			motDePasse = prop.getProperty("dbpassword");

		} catch (IOException ex) {
			ex.printStackTrace();
		} finally {
			if (input != null) {
				try {
					input.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public static Connection getConnexion() {

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
