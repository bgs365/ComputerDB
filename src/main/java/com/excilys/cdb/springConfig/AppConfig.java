package com.excilys.cdb.springConfig;
 
import java.util.ResourceBundle;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
 
@Configuration
@ComponentScan(basePackages = "com.excilys.cdb")
@PropertySource(value = { "classpath:config.properties" })
@EnableTransactionManagement
public class AppConfig {
 
    @Autowired
    private Environment env;
 
    @Bean
    public DataSource dataSource() {
    	HikariConfig config = new HikariConfig();
    	try {
				Class.forName("com.mysql.jdbc.Driver");
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
  		ResourceBundle input = ResourceBundle.getBundle("config");

  		String baseDeDonnee = input.getString("jdbc.url");
  		String login = input.getString("jdbc.username");
  		String password = input.getString("jdbc.password");
  		
  		config.setJdbcUrl(baseDeDonnee);
      config.setUsername(login);
      config.setPassword(password);  
      config.setDriverClassName("com.mysql.jdbc.Driver");
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