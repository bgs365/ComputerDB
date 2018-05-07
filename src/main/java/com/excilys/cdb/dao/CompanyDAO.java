package com.excilys.cdb.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.excilys.cdb.model.Company;
import com.excilys.cdb.model.Computer;

/**
 * *Classe qui permet de mettre en place la persistance d'un Computer.
 *
 * @autor Beydi SANOGO
 */

public enum CompanyDAO {
  INSTANCE;

  String requeteFindById = "SELECT id,name FROM company where id = ?";
  String requeteFinfAll = "SELECT id,name FROM company";
  String requeteFindLimitNumberOfResult = "SELECT id,name FROM company LIMIT ?, ?";
  String requeteFindByName = "SELECT id,name FROM company WHERE name LIKE ? ";
  static final Logger LOGGER = LoggerFactory.getLogger(CompanyDAO.class);

  /**
   * Find an company by id.
   *
   * @param id Company.id
   * @return Company
   */
  public Optional<Company> findById(int id) {
    Company company = new Company();

    try (Connection conn = Connexion.INSTANCE.getConnexion();
        PreparedStatement preparedStatement = conn.prepareStatement(requeteFindById)) {

      preparedStatement.setInt(1, id);

      ResultSet result = preparedStatement.executeQuery();

      while (result.next()) {
        company.setId(result.getInt("Id"));
        company.setName(result.getString("Name"));
        List<Computer> pComputers = ComputerDAO.INSTANCE.findByCompany(company.getId());
        company.setComputer(pComputers);
      }

    } catch (SQLException e) {
      LOGGER.info("Erreur sur la requete find Company by id : " + e.getMessage());
    }

    return Optional.ofNullable(company);
  }

  /**
   * find list of companies.
   *
   * @return List<Company>
   */
  public List<Company> findAll() {
    List<Company> companies = new ArrayList<Company>();

    try (Connection conn = Connexion.INSTANCE.getConnexion();
        PreparedStatement preparedStatement = conn.prepareStatement(requeteFinfAll)) {

      ResultSet result = preparedStatement.executeQuery();

      while (result.next()) {
        Company company = new Company();
        company.setId(result.getInt("Id"));
        company.setName(result.getString("Name"));
        List<Computer> pComputers = ComputerDAO.INSTANCE.findByCompany(company.getId());
        company.setComputer(pComputers);
        companies.add(company);

      }

    } catch (SQLException e) {
      LOGGER.info("Erreur sur la requete find all Company : " + e.getMessage());
    }

    return companies;
  }

  /**
   * find a bunch numberOfResultByPage of companies in db, which first company is
   * pageIndex.
   *
   * @param pageIndex asName
   * @param numberOfResultByPage asName
   * @return List<Company>
   */
  public List<Company> findLimitNumberOfResult(int pageIndex, int numberOfResultByPage) {

    List<Company> companies = new ArrayList<Company>();

    try (Connection conn = Connexion.INSTANCE.getConnexion();
        PreparedStatement preparedStatement = conn.prepareStatement(requeteFindLimitNumberOfResult)) {
      preparedStatement.setInt(1, pageIndex);
      preparedStatement.setInt(2, numberOfResultByPage);
      ResultSet result = preparedStatement.executeQuery();

      while (result.next()) {
        Company company = new Company();
        company.setId(result.getInt("Id"));
        company.setName(result.getString("Name"));
        List<Computer> pComputers = ComputerDAO.INSTANCE.findByCompany(company.getId());
        company.setComputer(pComputers);
        companies.add(company);

      }

    } catch (SQLException e) {
      LOGGER.info("Erreur sur la requete find company by name : " + e.getMessage());
    }

    return companies;
  }

  /**
   * find company by name.
   *
   * @param name asName
   * @return Company
   */
  public List<Company> findByName(String name) {

    List<Company> companies = new ArrayList<Company>();

    try (Connection conn = Connexion.INSTANCE.getConnexion();
        PreparedStatement preparedStatement = conn.prepareStatement(requeteFindByName)) {
      preparedStatement.setString(1, "%"+name+"%");
      ResultSet result = preparedStatement.executeQuery();

      while (result.next()) {
      	Company company = new Company();
        company.setId(result.getInt("Id"));
        company.setName(result.getString("Name"));
        List<Computer> pComputers = ComputerDAO.INSTANCE.findByCompany(company.getId());
        company.setComputer(pComputers);
        companies.add(company);
      }

    } catch (SQLException e) {
      LOGGER.info("Erreur sur la requete find company by name : " + e.getMessage());
    }

    return companies;
  }

}