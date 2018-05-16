package com.excilys.cdb.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ResourceBundle;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

public enum Connexion {
	INSTANCE;

	static String driver = "com.mysql.jdbc.Driver";
	static String baseDeDonnee = null;
	static String login = null;
	static String password = null;
	static final Logger LOGGER = LoggerFactory.getLogger(Connexion.class);
	private static HikariDataSource ds;

	/**
	 * load connexion variables.
	 */
	static{
		HikariConfig config = new HikariConfig();
		 
		ResourceBundle input = ResourceBundle.getBundle("config");

		baseDeDonnee = input.getString("jdbc.url");
		login = input.getString("jdbc.username");
		password = input.getString("jdbc.password");
		
		config.setJdbcUrl(baseDeDonnee);
    config.setUsername(login);
    config.setPassword(password);  
    config.setDriverClassName(driver);
    config.addDataSourceProperty("cachePrepStmts", "true");
    config.addDataSourceProperty("prepStmtCacheSize", "250");
    config.addDataSourceProperty("prepStmtCacheSqlLimit", "2048");
    ds = new HikariDataSource(config);
	}

	/**
	 * make connexion to db.
	 *
	 * @return Connection
	 */
	public Connection getConnexion() {

		try {
			Class.forName(driver);
		} catch (ClassNotFoundException e) {
			LOGGER.error("Where is your MySQL JDBC Driver?", e);
		}
		Connection connection = null;

		try {
			//connection = DriverManager.getConnection(baseDeDonnee, login, password);
			connection = ds.getConnection();
		} catch (SQLException e) {
			LOGGER.error("Connection Failed! Check output console", e);

		}

		return connection;
	}

}