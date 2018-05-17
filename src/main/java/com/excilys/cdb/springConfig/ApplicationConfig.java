package com.excilys.cdb.springConfig;

import java.util.ResourceBundle;
import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

/**
 * Do all configurations.
 * @author sanogo
 *
 */
@Configuration
@ComponentScan(basePackages = "com.excilys.cdb")
@EnableTransactionManagement
public class ApplicationConfig {
	static final Logger LOGGER = LoggerFactory.getLogger(ApplicationConfig.class);

	@Bean
	public DataSource dataSource() {
		HikariConfig config = new HikariConfig();

		ResourceBundle input = ResourceBundle.getBundle("config");
		try {
			Class.forName(input.getString("jdbc.driverClassName"));
		} catch (ClassNotFoundException e) {
			LOGGER.error(e.getMessage());
		}
		config.setJdbcUrl(input.getString("jdbc.url"));
		config.setUsername(input.getString("jdbc.username"));
		config.setPassword(input.getString("jdbc.password"));
		config.setDriverClassName(input.getString("jdbc.driverClassName"));
		config.addDataSourceProperty("cachePrepStmts", "true");
		config.addDataSourceProperty("prepStmtCacheSize", "250");
		config.addDataSourceProperty("prepStmtCacheSqlLimit", "2048");
		return new HikariDataSource(config);
	}

	@Bean
	public JdbcTemplate jdbcTemplate(DataSource dataSource) {
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		jdbcTemplate.setResultsMapCaseInsensitive(true);
		return jdbcTemplate;
	}

}