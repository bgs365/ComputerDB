package com.excilys.cdb.dao;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.excilys.cdb.mapper.CompanyMapper;
import com.excilys.cdb.model.Company;

/**
 * *Classe qui permet de mettre en place la persistance d'un Computer.
 *
 * @autor Beydi SANOGO
 */
@Repository
public class CompanyDAO {

	private JdbcTemplate jdbcTemplate;

	public CompanyDAO(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	String requeteFindById = "SELECT id,name FROM company where id = ?";
	String requeteFinfAll = "SELECT id,name FROM company";
	String requeteFindLimitNumberOfResult = "SELECT id,name FROM company LIMIT ?, ?";
	String requeteFindByName = "SELECT id,name FROM company WHERE name LIKE ? ";
	String requeteDeleteComputer = "DELETE FROM computer WHERE company_id = ?";
	String requeteDeleteCompany = "DELETE FROM company WHERE id = ?";
	String requeteFindCompanyComputers = "SELECT  company.id,company.name,computer.id,computer.name,computer.introduced,computer.discontinued  FROM company LEFT OUTER JOIN computer ON computer.company_id=company.id WHERE company.id = ?";
	static final Logger LOGGER = LoggerFactory.getLogger(CompanyDAO.class);

	/**
	 * Find an company by id.
	 *
	 * @param id
	 *          Company.id
	 * @return Company
	 */
	public Optional<Company> findById(int id) {
		Company company = (Company) jdbcTemplate.queryForObject(requeteFindById, new CompanyMapper(), id);
		return Optional.ofNullable(company);
	}

	/**
	 * find list of companies.
	 *
	 * @return List<Company>
	 */
	public List<Company> findAll() {
		List<Company> companies = jdbcTemplate.query(requeteFinfAll, new CompanyMapper());
		return companies;
	}

	/**
	 * find a bunch numberOfResultByPage of companies in db, which first company is
	 * pageIndex.
	 *
	 * @param pageIndex
	 *          asName
	 * @param numberOfResultByPage
	 *          asName
	 * @return List<Company>
	 */
	public List<Company> findLimitNumberOfResult(int pageIndex, int numberOfResultByPage) {
		List<Company> companies = jdbcTemplate.query(requeteFindLimitNumberOfResult, new CompanyMapper(), pageIndex, numberOfResultByPage);
		return companies;
	}

	/**
	 * find company by name.
	 *
	 * @param name
	 *          asName
	 * @return Company
	 */
	public List<Company> findByName(String name) {
		List<Company> companies = jdbcTemplate.query(requeteFindByName, new CompanyMapper(), "%" + name + "%");
		return companies;
	}

	/**
	 * Delete company from Db and return 1 in case of success and 0 if not.
	 *
	 * @param id
	 *          asName
	 * @return (0 or 1)
	 */
	public int delete(int id) {
		jdbcTemplate.update(requeteDeleteComputer,id);
		return  jdbcTemplate.update(requeteDeleteCompany, id);
	}

}