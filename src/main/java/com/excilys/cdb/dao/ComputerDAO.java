package com.excilys.cdb.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.excilys.cdb.model.Company;
import com.excilys.cdb.model.Computer;

public enum ComputerDAO {
	INSTANCE;
	String requeteFindById = "SELECT * FROM computer LEFT JOIN company ON company.id = computer.company_id WHERE computer.id = ?";
	String requeteFinfAll = "SELECT * FROM computer LEFT JOIN company ON company.id = computer.company_id ";
	String requeteFindByName = "SELECT * FROM computer LEFT JOIN company ON company.id = computer.company_id  WHERE company.name= ? ";
	String requeteFindLimitNumberOfResult = "SELECT * FROM computer LEFT JOIN company ON company.id = computer.company_id LIMIT ?, ?";
	String requeteFindByCompany = "SELECT * FROM computer LEFT JOIN company ON company.id = computer.company_id WHERE computer.company_id = ?";
	String requeteInsert = "INSERT INTO computer (NAME, INTRODUCED, DISCONTINUED, COMPANY_ID) VALUES (?,?,?,?)";
	String requeteInsertSansCompany = "INSERT INTO computer (NAME, INTRODUCED, DISCONTINUED) VALUES (?,?,?)";
	String requetedelete = "DELETE FROM computer WHERE id = ?";
	String requeteUpdate = "UPDATE computer SET NAME = ? ,INTRODUCED = ? ,DISCONTINUED = ? ,COMPANY_ID = ? WHERE Id = ?";
	String requeteUpdateChangerCompany = "UPDATE computer SET COMPANY_ID = ?  WHERE Id = ?";

	public Computer findById(int id) {
		Computer computer = new Computer();

		try (Connection conn = Connexion.getConnexion();PreparedStatement preparedStatement = conn.prepareStatement(requeteFindById)) {

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

		} catch (SQLException e) {
			System.err.println("Erreur sur la requete find Company by id : " + e.getMessage());
		}

		return computer;

	}

	public List<Computer> findAll() {
		List<Computer> computers = new ArrayList<Computer>();

		try (Connection conn = Connexion.getConnexion();
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
			System.err.println("Erreur sur la requete find Company by id : " + e.getMessage());
		}

		return computers;

	}

	public List<Computer> findLimitNumberOfResult(int pageIndex, int numberOfResultByPage) {
		List<Computer> computers = new ArrayList<Computer>();

		try (Connection conn = Connexion.getConnexion();
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
			System.err.println("Erreur sur la requete find Company by id : " + e.getMessage());
		}
		return computers;
	}

	public List<Computer> findByName(Computer example) {
		List<Computer> computers = new ArrayList<Computer>();

		try (Connection conn = Connexion.getConnexion();
				PreparedStatement preparedStatement = conn.prepareStatement(requeteFindByName)) {

			preparedStatement.setString(1, example.getName());
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
			System.err.println("Erreur sur la requete find Company by id : " + e.getMessage());
		}

		return computers;
	}

	public List<Computer> findByCompany(int companyId) {
		List<Computer> computers = new ArrayList<Computer>();

		try (Connection conn = Connexion.getConnexion();
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
			System.err.println("Erreur sur la requete find Company by id : " + e.getMessage());
		}

		return computers;
	}

	/** Enregistrement **/
	public int save(Computer computer) {
		Connection conn = Connexion.getConnexion();
		int reussite = 0;
		PreparedStatement preparedStatement = null;
		try {

			if (computer.getCompany() != null) {
				preparedStatement = (PreparedStatement) conn.prepareStatement(requeteInsert);
				preparedStatement.setInt(4, computer.getCompany().getId());

			} else {
				preparedStatement = (PreparedStatement) conn.prepareStatement(requeteInsertSansCompany);
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

			// System.out.println(e.getMessage());
			System.out.println(computer.getCompany().getName() + " N'existe pas");

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

	/** Supression **/
	public int delete(Computer computer) {
		int reussite = 0;
		try (Connection conn = Connexion.getConnexion();
				PreparedStatement preparedStatement = conn.prepareStatement(requetedelete)) {
			preparedStatement.setInt(1, computer.getId());

			// execute delete SQL stetement
			reussite = preparedStatement.executeUpdate();

			System.out.println(computer.getName() + " a été bien supprimé!");

		} catch (SQLException e) {

			System.out.println(e.getMessage());

		}
		return reussite;
	}

	/** Update **/
	public int update(Computer computer) {
		int reussite = 0;
		try (Connection conn = Connexion.getConnexion();
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
			System.out.println(computer.getName() + " a été bien modifié!");

		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return reussite;
	}

}