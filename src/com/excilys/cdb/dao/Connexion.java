package com.excilys.cdb.dao;

import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.SQLException;

public enum Connexion {

	INSTANCE;

	static String driver = "com.mysql.jdbc.Driver";
	static String baseDeDonnee = "jdbc:mysql://localhost:3306/computer-database-db?autoReconnect=true&useSSL=false";
	static String login = "admincdb";
	static String motDePasse = "qwerty1234";

	public static Connection getConnexion() {
		// System.out.println("-------- MySQL JDBC Connection Testing ------------");

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
