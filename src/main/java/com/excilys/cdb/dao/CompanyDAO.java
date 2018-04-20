package com.excilys.cdb.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.excilys.cdb.model.Company;
import com.excilys.cdb.model.Computer;
import com.mysql.jdbc.PreparedStatement;

public enum CompanyDAO {
	INSTANCE;

	String requeteFindById = "SELECT * FROM company where id = ?";
	String requeteFinfAll = "SELECT * FROM company";
	String requeteFindLimitNumberOfResult = "SELECT * FROM company LIMIT ?, ?";
	String requeteFindByName = "SELECT * FROM company WHERE name= ? ";

	public Company findById(int id) {
		Company company = new Company();

		Connection conn = Connexion.getConnexion();
		PreparedStatement preparedStatement = null;

		try {
			preparedStatement = (PreparedStatement) conn.prepareStatement(requeteFindById);

			preparedStatement.setInt(1, id);

			ResultSet result = preparedStatement.executeQuery();

			while (result.next()) {
				company.setId(result.getInt("Id"));
				company.setName(result.getString("Name"));
				List<Computer> pComputers = ComputerDAO.INSTANCE.findByCompany(company.getId());
				company.setComputer(pComputers);
			}

		} catch (SQLException e) {
			System.err.println("Erreur sur la requete find Company by id : " + e.getMessage());
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

		return company;
	}

	public List<Company> findAll() {
		List<Company> companies = new ArrayList<Company>();
		Connection conn = Connexion.getConnexion();
		PreparedStatement preparedStatement = null;

		try {
			preparedStatement = (PreparedStatement) conn.prepareStatement(requeteFinfAll);

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
			System.err.println("Erreur sur la requete find all Company : " + e.getMessage());
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

		return companies;
	}

	public List<Company> findLimitNumberOfResult(int pageIndex, int numberOfResultByPage) {

		List<Company> companies = new ArrayList<Company>();

		Connection conn = Connexion.getConnexion();
		PreparedStatement preparedStatement = null;

		try {
			preparedStatement = (PreparedStatement) conn.prepareStatement(requeteFindLimitNumberOfResult);
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
			System.err.println("Erreur sur la requete find company by name : " + e.getMessage());
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

		return companies;
	}

	public Company findByName(String name) {

		Company company = new Company();

		Connection conn = Connexion.getConnexion();
		PreparedStatement preparedStatement = null;

		try {
			preparedStatement = (PreparedStatement) conn.prepareStatement(requeteFindByName);
			preparedStatement.setString(1, name);
			ResultSet result = preparedStatement.executeQuery();

			while (result.next()) {
				company.setId(result.getInt("Id"));
				company.setName(result.getString("Name"));
				List<Computer> pComputers = ComputerDAO.INSTANCE.findByCompany(company.getId());
				company.setComputer(pComputers);

			}

		} catch (SQLException e) {
			System.err.println("Erreur sur la requete find company by name : " + e.getMessage());
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

		return company;
	}

}
