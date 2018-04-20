package com.excilys.cdb.dao;

import java.sql.DriverManager;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

public enum Connexion {

	INSTANCE;
	
	static String driver = "com.mysql.jdbc.Driver";
	static String baseDeDonnee = "jdbc:mysql://localhost:3306/computer-database-db?autoReconnect=true&useSSL=false";
	static String login = "admincdb";
	static String motDePasse = "qwerty1234";
	
	public static void getConnexionVariables() {
		Properties prop = new Properties();
		InputStream input = null;

		try {

			input = new FileInputStream("config.properties");

			// load a properties file
			prop.load(input);

			// get the property value and print it out
			baseDeDonnee = prop.getProperty("database");
			login = prop.getProperty("dbuser");
			motDePasse = prop.getProperty("dbpassword");
			/*
			System.out.println(prop.getProperty("database"));
			System.out.println(prop.getProperty("dbuser"));
			System.out.println(prop.getProperty("dbpassword"));*/

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
		// System.out.println("-------- MySQL JDBC Connection Testing ------------");
		getConnexionVariables();
		try {
			Class.forName(driver);
		} catch (ClassNotFoundException e) {
			// System.out.println("Where is your MySQL JDBC Driver?");
			e.printStackTrace();
		}

		// System.out.println("MySQL JDBC Driver Registered!");
		Connection connection = null;

		try {
			connection = DriverManager.getConnection(baseDeDonnee, login, motDePasse);
		} catch (SQLException e) {
			System.out.println("Connection Failed! Check output console");
			e.printStackTrace();
		}

		return connection;
	}

}
