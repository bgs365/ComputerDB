package com.excilys.cdb.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.excilys.cdb.model.Company;
import com.excilys.cdb.model.Computer;
import com.excilys.mapper.ComputerMapper;

/**
 * *Classe qui permet de mettre en place la persistance d'une Company.
 *
 * @autor Beydi SANOGO
 */
@Repository
public class ComputerDAO {

	private DataSource dataSource;
	private JdbcTemplate jdbcTemplate;

	@Autowired
	public ComputerDAO(JdbcTemplate jdbcTemplate, DataSource dataSource) {
		this.jdbcTemplate = jdbcTemplate;
		this.dataSource = dataSource;
	}

	String requeteFindById = "SELECT computer.id,computer.name,computer.introduced,computer.discontinued,company.id,company.name FROM computer LEFT JOIN company ON company.id = computer.company_id WHERE computer.id = ?";
	String requeteFinfAll = "SELECT computer.id,computer.name,computer.introduced,computer.discontinued,company.id,company.name FROM computer LEFT JOIN company ON company.id = computer.company_id ";
	String requeteFindByName = "SELECT computer.id,computer.name,computer.introduced,computer.discontinued,company.id,company.name FROM computer LEFT JOIN company ON company.id = computer.company_id  WHERE computer.name LIKE ?  ";
	String requeteFindByComputerAndCompanyNameLimit = " SELECT computer.id,computer.name,computer.introduced,computer.discontinued,company.id,company.name FROM computer LEFT JOIN company ON company.id = computer.company_id  WHERE computer.name LIKE ? OR company.name LIKE ? ORDER BY computer.name LIMIT ?, ?";
	String requeteFindByComputerAndCompanyName = " SELECT computer.id,computer.name,computer.introduced,computer.discontinued,company.id,company.name FROM computer LEFT JOIN company ON company.id = computer.company_id  WHERE computer.name LIKE ? OR company.name LIKE ? ORDER BY computer.name";
	String requeteFindLimitNumberOfResult = "SELECT computer.id,computer.name,computer.introduced,computer.discontinued,company.id,company.name FROM computer LEFT JOIN company ON company.id = computer.company_id LIMIT ?, ?";
	String requeteFindByCompany = "SELECT computer.id,computer.name,computer.introduced,computer.discontinued,company.id,company.name FROM computer LEFT JOIN company ON company.id = computer.company_id WHERE computer.company_id = ?";
	String requeteInsert = "INSERT INTO computer (NAME, INTRODUCED, DISCONTINUED, COMPANY_ID) VALUES (?,?,?,?)";
	String requeteInsertSansCompany = "INSERT INTO computer (NAME, INTRODUCED, DISCONTINUED) VALUES (?,?,?)";
	String requetedelete = "DELETE FROM computer WHERE id = ?";
	String requeteUpdate = "UPDATE computer SET NAME = ? ,INTRODUCED = ? ,DISCONTINUED = ? ,COMPANY_ID = ? WHERE Id = ?";
	String requeteUpdateChangerCompany = "UPDATE computer SET COMPANY_ID = ?  WHERE Id = ?";
	static final Logger LOGGER = LoggerFactory.getLogger(ComputerDAO.class);

	/**
	 * allow access to an computer with is id.
	 *
	 * @param id
	 *          asName
	 * @return Computer
	 */
	public Optional<Computer> findById(int id) {
		Computer computer = (Computer) jdbcTemplate.queryForObject(requeteFindById, new ComputerMapper(), id);
		return Optional.ofNullable(computer);

	}

	/**
	 * Allow access to all computers of database.
	 *
	 * @return List<Computer>
	 */
	public List<Computer> findAll() {
		List<Computer> computers = jdbcTemplate.query(requeteFinfAll, new ComputerMapper());
		return computers;
	}

	/**
	 * Use to generate pages.
	 *
	 * @param pageIndex
	 *          asName
	 * @param numberOfResultByPage
	 *          asName
	 * @return List<Computer>
	 */
	public List<Computer> findLimitNumberOfResult(int pageIndex, int numberOfResultByPage) {
		List<Computer> computers = jdbcTemplate.query(requeteFindLimitNumberOfResult, new ComputerMapper(), pageIndex,
		    numberOfResultByPage);
		return computers;
	}

	/**
	 * Return all computer with name past as parameter.
	 *
	 * @param name
	 *          asName
	 * @return List<Computer>
	 */
	public List<Computer> findByName(String name) {
		List<Computer> computers = jdbcTemplate.query(requeteFindByName, new ComputerMapper(), "%" + name + "%");
		return computers;
	}

	/**
	 * find company by id.
	 *
	 * @param companyId
	 *          asName
	 * @return List<Computer>
	 */
	public List<Computer> findByCompany(int companyId) {
		List<Computer> computers = jdbcTemplate.query(requeteFindByCompany, new ComputerMapper(), companyId);

		return computers;
	}

	/**
	 * for page.
	 * 
	 * @param name
	 * @param pageIndex
	 * @param numberOfResultByPage
	 * @return
	 */
	public List<Computer> findByComputerAndCompanyNameLimit(String name, int pageIndex, int numberOfResultByPage) {
		List<Computer> computers = jdbcTemplate.query(requeteFindByComputerAndCompanyNameLimit, new ComputerMapper(),
		    "%" + name + "%", "%" + name + "%", pageIndex, numberOfResultByPage);
		return computers;
	}

	/**
	 * use when page not need.
	 * 
	 * @param name
	 * @param pageIndex
	 * @param numberOfResultByPage
	 * @return
	 */
	public List<Computer> findByComputerAndCompanyName(String name) {
		List<Computer> computers = jdbcTemplate.query(requeteFindByComputerAndCompanyName, new ComputerMapper(),
		    "%" + name + "%", "%" + name + "%");
		return computers;
	}

	/**
	 * Save computer in Db and return 1 in case of success and 0 if not.
	 *
	 * @param computer
	 *          asName
	 * @return (0 or 1)
	 */
	public int save(Computer computer) {
		java.sql.Date introduced = (computer.getIntroduced() != null) ? java.sql.Date.valueOf(computer.getIntroduced())
		    : null;
		java.sql.Date discontinued = (computer.getDiscontinued() != null)
		    ? java.sql.Date.valueOf(computer.getDiscontinued())
		    : null;
		Company company = (computer.getCompany() != null) ? computer.getCompany() : null;
		if (findByCompany(computer.getCompany().getId()).isEmpty()) {
			return jdbcTemplate.update(requeteInsert, computer.getName(), introduced, discontinued, null);
		} else {
			return jdbcTemplate.update(requeteInsert, computer.getName(), introduced, discontinued,
			    computer.getCompany().getId());
		}

	}

	/**
	 * Delete computer from Db and return 1 in case of success and 0 if not.
	 *
	 * @param id
	 *          asName
	 * @return (0 or 1)
	 */
	public int delete(int id) {
		return jdbcTemplate.update(requetedelete, id);
	}

	/**
	 * Update computer in Db and return 1 in case of success and 0 if not.
	 *
	 * @param computer
	 *          asName
	 * @return (0 or 1)
	 */
	public int update(Computer computer) {
		int reussite = 0;
		try (Connection conn = dataSource.getConnection();
		    PreparedStatement preparedStatement = conn.prepareStatement(requeteUpdate)) {

			preparedStatement.setString(1, computer.getName());
			if (computer.getIntroduced() != null) {
				preparedStatement.setDate(2, java.sql.Date.valueOf(computer.getIntroduced()));
			} else {
				preparedStatement.setDate(2, null);
			}

			if (computer.getDiscontinued() != null) {
				preparedStatement.setDate(3, java.sql.Date.valueOf(computer.getDiscontinued()));
			} else {
				preparedStatement.setDate(3, null);
			}

			if (computer.getCompany() == null) {
				preparedStatement.setString(4, null);
			} else if (findByCompany(computer.getCompany().getId()).isEmpty()) {
				preparedStatement.setString(4, null);
			} else {
				preparedStatement.setInt(4, computer.getCompany().getId());
			}

			preparedStatement.setInt(5, computer.getId());
			reussite = preparedStatement.executeUpdate();
			LOGGER.info(computer.getName() + " a été bien modifié!");

		} catch (SQLException e) {
			LOGGER.info(e.getMessage());
		}
		return reussite;
	}

}
