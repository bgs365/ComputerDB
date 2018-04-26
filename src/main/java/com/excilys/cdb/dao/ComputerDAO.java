package com.excilys.cdb.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.excilys.cdb.model.Company;
import com.excilys.cdb.model.Computer;

/**
 * *Classe qui permet de mettre en place la persistance d'une Company.
 *
 * @autor Beydi SANOGO
 */

public enum ComputerDAO {
  INSTANCE;

  String requeteFindById = "SELECT * FROM computer LEFT JOIN company ON company.id = computer.company_id WHERE computer.id = ?";
  String requeteFinfAll = "SELECT * FROM computer LEFT JOIN company ON company.id = computer.company_id ";
  String requeteFindByName = "SELECT * FROM computer LEFT JOIN company ON company.id = computer.company_id  WHERE computer.name= ? ";
  String requeteFindLimitNumberOfResult = "SELECT * FROM computer LEFT JOIN company ON company.id = computer.company_id LIMIT ?, ?";
  String requeteFindByCompany = "SELECT * FROM computer LEFT JOIN company ON company.id = computer.company_id WHERE computer.company_id = ?";
  String requeteInsert = "INSERT INTO computer (NAME, INTRODUCED, DISCONTINUED, COMPANY_ID) VALUES (?,?,?,?)";
  String requeteInsertSansCompany = "INSERT INTO computer (NAME, INTRODUCED, DISCONTINUED) VALUES (?,?,?)";
  String requetedelete = "DELETE FROM computer WHERE id = ?";
  String requeteUpdate = "UPDATE computer SET NAME = ? ,INTRODUCED = ? ,DISCONTINUED = ? ,COMPANY_ID = ? WHERE Id = ?";
  String requeteUpdateChangerCompany = "UPDATE computer SET COMPANY_ID = ?  WHERE Id = ?";
  static final Logger LOGGER = LoggerFactory.getLogger(ComputerDAO.class);

  /**
   * allow access to an computer with is id.
   *
   * @param id asName
   * @return Computer
   */
  public Computer findById(int id) {
    Computer computer = new Computer();

    try (Connection conn = Connexion.INSTANCE.getConnexion();
        PreparedStatement preparedStatement = conn.prepareStatement(requeteFindById)) {

      preparedStatement.setInt(1, id);
      ResultSet result = preparedStatement.executeQuery();

      while (result.next()) {
        Company company = new Company();
        computer.setId(result.getInt("computer.Id"));
        computer.setName(result.getString("computer.Name"));
        if (result.getDate("computer.introduced") != null) {
          computer.setIntroduced(result.getDate("computer.introduced").toLocalDate());
        }
        if (result.getDate("computer.discontinued") != null) {
          computer.setDiscontinued(result.getDate("computer.discontinued").toLocalDate());
        }
        if (result.getInt("company.Id") != 0) {
          company.setId(result.getInt("company.Id"));
          company.setName(result.getString("company.name"));
        }
        computer.setCompany(company);
      }

    } catch (SQLException | NullPointerException e) {
      LOGGER.info("Erreur sur la requete find Computer by id : " + e.getMessage());
    }

    return computer;

  }

  /**
   * Allow access to all computers of database.
   *
   * @return List<Computer>
   */
  public List<Computer> findAll() {
    List<Computer> computers = new ArrayList<Computer>();

    try (Connection conn = Connexion.INSTANCE.getConnexion();
        PreparedStatement preparedStatement = conn.prepareStatement(requeteFinfAll)) {

      ResultSet result = preparedStatement.executeQuery();

      while (result.next()) {

        Computer computer = new Computer();
        computer.setId(result.getInt("Id"));
        computer.setName(result.getString("Name"));
        Company company = new Company();
        if (result.getDate("introduced") != null) {
          computer.setIntroduced(result.getDate("introduced").toLocalDate());
        }
        if (result.getDate("discontinued") != null) {
          computer.setDiscontinued(result.getDate("discontinued").toLocalDate());
        }
        if (result.getInt("company.Id") != 0) {
          company.setId(result.getInt("company.Id"));
          company.setName(result.getString("company.name"));
        }
        computer.setCompany(company);
        computers.add(computer);
      }
    } catch (SQLException e) {
      LOGGER.info("Erreur sur la requete findAll Computer  : " + e.getMessage());
    }

    return computers;

  }

  /**
   * Use to generate pages.
   *
   * @param pageIndex asName
   * @param numberOfResultByPage asName
   * @return List<Computer>
   */
  public List<Computer> findLimitNumberOfResult(int pageIndex, int numberOfResultByPage) {
    List<Computer> computers = new ArrayList<Computer>();

    try (Connection conn = Connexion.INSTANCE.getConnexion();
        PreparedStatement preparedStatement = conn.prepareStatement(requeteFindLimitNumberOfResult)) {

      preparedStatement.setInt(1, pageIndex);
      preparedStatement.setInt(2, numberOfResultByPage);
      ResultSet result = preparedStatement.executeQuery();

      while (result.next()) {

        Computer computer = new Computer();
        computer.setId(result.getInt("Id"));
        computer.setName(result.getString("Name"));
        Company company = new Company();
        if (result.getDate("introduced") != null) {
          computer.setIntroduced(result.getDate("introduced").toLocalDate());
        }
        if (result.getDate("discontinued") != null) {
          computer.setDiscontinued(result.getDate("discontinued").toLocalDate());
        }
        if (result.getInt("company.Id") != 0) {
          company.setId(result.getInt("company.Id"));
          company.setName(result.getString("company.name"));
        }
        computer.setCompany(company);
        computers.add(computer);
      }

    } catch (SQLException e) {

      LOGGER.info("Erreur sur la requete find limit NUmber of computer  : " + e.getMessage());
    }
    return computers;
  }

  /**
   * Return all computer with name past as parameter.
   *
   * @param name asName
   * @return List<Computer>
   */
  public List<Computer> findByName(String name) {
    List<Computer> computers = new ArrayList<Computer>();

    try (Connection conn = Connexion.INSTANCE.getConnexion();
        PreparedStatement preparedStatement = conn.prepareStatement(requeteFindByName)) {

      preparedStatement.setString(1, name);
      ResultSet result = preparedStatement.executeQuery();

      while (result.next()) {

        Computer computer = new Computer();
        computer.setId(result.getInt("Id"));
        computer.setName(result.getString("Name"));
        Company company = new Company();
        if (result.getDate("introduced") != null) {
          computer.setIntroduced(result.getDate("introduced").toLocalDate());
        }
        if (result.getDate("discontinued") != null) {
          computer.setDiscontinued(result.getDate("discontinued").toLocalDate());
        }
        if (result.getInt("company.Id") != 0) {
          company.setId(result.getInt("company.Id"));
          company.setName(result.getString("company.name"));
        }
        computer.setCompany(company);
        computers.add(computer);
      }

    } catch (SQLException e) {
      LOGGER.info("Erreur sur la requete find Computer by name : " + e.getMessage());
    }

    return computers;
  }

  /**
   * find company by id.
   *
   * @param companyId asName
   * @return List<Computer>
   */
  public List<Computer> findByCompany(int companyId) {
    List<Computer> computers = new ArrayList<Computer>();

    try (Connection conn = Connexion.INSTANCE.getConnexion();
        PreparedStatement preparedStatement = conn.prepareStatement(requeteFindByCompany)) {
      preparedStatement.setInt(1, companyId);
      ResultSet result = preparedStatement.executeQuery();

      while (result.next()) {

        Computer computer = new Computer();
        computer.setId(result.getInt("Id"));
        computer.setName(result.getString("Name"));
        Company company = new Company();
        if (result.getDate("introduced") != null) {
          computer.setIntroduced(result.getDate("introduced").toLocalDate());
        }
        if (result.getDate("discontinued") != null) {
          computer.setDiscontinued(result.getDate("discontinued").toLocalDate());
        }
        if (result.getInt("company.Id") != 0) {
          company.setId(result.getInt("company.Id"));
          company.setName(result.getString("company.name"));
        }
        computer.setCompany(company);
        computers.add(computer);
      }

    } catch (SQLException e) {
      LOGGER.info("Erreur sur la requete find Computer by Company id : " + e.getMessage());
    }

    return computers;
  }

  /**
   * Save computer in Db and return 1 in case of success and 0 if not.
   *
   * @param computer asName
   * @return (0 or 1)
   */
  public int save(Computer computer) {
    Connection conn = Connexion.INSTANCE.getConnexion();
    int reussite = 0;
    PreparedStatement preparedStatement = null;
    try {

      if (computer.getCompany() != null) {
        preparedStatement = conn.prepareStatement(requeteInsert);
        preparedStatement.setInt(4, computer.getCompany().getId());

      } else {
        preparedStatement = conn.prepareStatement(requeteInsertSansCompany);
      }

      preparedStatement.setString(1, computer.getName());

      if (computer.getIntroduced() == null) {
        preparedStatement.setDate(2, null);
      } else {
        preparedStatement.setDate(2, java.sql.Date.valueOf(computer.getIntroduced()));
      }

      if (computer.getDiscontinued() == null) {
        preparedStatement.setDate(3, null);
      } else {
        preparedStatement.setDate(3, java.sql.Date.valueOf(computer.getDiscontinued()));
      }

      // execute insert SQL stetement
      reussite = preparedStatement.executeUpdate();

      System.out.println(computer.getName() + " bien créee");

    } catch (SQLException e) {
      LOGGER.info(computer.getCompany().getName() + " N'existe pas");
    } finally {

      if (preparedStatement != null) {
        try {
          preparedStatement.close();
        } catch (SQLException e) {
          e.printStackTrace();
        }
      }
      if (conn != null) {
        try {
          conn.close();
        } catch (SQLException e) {
          e.printStackTrace();
        }
      }

    }
    return reussite;
  }

  /**
   * Delete computer from Db and return 1 in case of success and 0 if not.
   *
   * @param id asName
   * @return (0 or 1)
   */
  public int delete(int id) {
    int reussite = 0;
    try (Connection conn = Connexion.INSTANCE.getConnexion();
        PreparedStatement preparedStatement = conn.prepareStatement(requetedelete)) {
      preparedStatement.setInt(1, id);

      // execute delete SQL stetement
      reussite = preparedStatement.executeUpdate();

      LOGGER.info(" le pc qui a pour id : " + id + " a été bien supprimé!");

    } catch (SQLException e) {

      LOGGER.info(e.getMessage());

    }
    return reussite;
  }

  /**
   * Update computer in Db and return 1 in case of success and 0 if not.
   *
   * @param computer asName
   * @return (0 or 1)
   */
  public int update(Computer computer) {
    int reussite = 0;
    try (Connection conn = Connexion.INSTANCE.getConnexion();
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

      if (computer.getCompany() != null) {
        preparedStatement.setInt(4, computer.getCompany().getId());
      } else {
        preparedStatement.setInt(4, 0);
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
