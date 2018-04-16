package com.excilys.cdb.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.excilys.cdb.model.Computer;
import com.mysql.jdbc.PreparedStatement;

public class ComputerDAO {
	
	String requeteFindById = "SELECT * FROM computer where id = ?";
	String requeteFinfAll = "SELECT * FROM computer";
	String requeteFindByName = "SELECT * FROM computer WHERE name= ? ";
	
	public Computer findById(int id) {
		Computer computer = new Computer();
		
		Connection conn=Connexion.getConnexion();
		PreparedStatement preparedStatement = null;
		
		try {
			
			preparedStatement = (PreparedStatement) conn.prepareStatement(requeteFindById);
			preparedStatement.setInt(1, id);

			ResultSet result = preparedStatement.executeQuery();
			
			while (result.next()) {
				computer.setId(result.getInt("Id"));
				computer.setName(result.getString("Name"));
				if( result.getDate("introduced")!=null ){
					computer.setIntroduced( result.getDate("introduced").toLocalDate() );
				}
				if( result.getDate("discontinued")!=null ) {
					computer.setDiscontinued( result.getDate("discontinued").toLocalDate() );
				}
				
			}
			
		} catch (SQLException e) {
			 System.err.println("Erreur sur la requete find Company by id : "+e.getMessage());
		}finally {
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

		return computer;
		
	}
	
	public List<Computer> findAll(){
		List<Computer> computers = new ArrayList<Computer>();
		
		Connection conn=Connexion.getConnexion();
		PreparedStatement preparedStatement = null;
		
		try {
			
			preparedStatement = (PreparedStatement) conn.prepareStatement(requeteFinfAll);

			ResultSet result = preparedStatement.executeQuery();
			
			while (result.next()) {
				
				Computer computer = new Computer();
				computer.setId(result.getInt("Id"));
				computer.setName(result.getString("Name"));
				if( result.getDate("introduced")!=null ){
					computer.setIntroduced( result.getDate("introduced").toLocalDate() );
				}
				if( result.getDate("discontinued")!=null ) {
					computer.setDiscontinued( result.getDate("discontinued").toLocalDate() );
				}
				computers.add(computer);
			}
			
		} catch (SQLException e) {
			 System.err.println("Erreur sur la requete find Company by id : "+e.getMessage());
		}finally {
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

		return computers;
		
	}
	
	public List<Computer> findByName(Computer example){
		List<Computer> computers = new ArrayList<Computer>();
		
		Connection conn=Connexion.getConnexion();
		PreparedStatement preparedStatement = null;
		
		try {
			
			preparedStatement = (PreparedStatement) conn.prepareStatement(requeteFindByName);
			preparedStatement.setString(1, example.getName());
			ResultSet result = preparedStatement.executeQuery();
			
			while (result.next()) {
				
				Computer computer = new Computer();
				computer.setId(result.getInt("Id"));
				computer.setName(result.getString("Name"));
				if( result.getDate("introduced")!=null ){
					computer.setIntroduced( result.getDate("introduced").toLocalDate() );
				}
				if( result.getDate("discontinued")!=null ) {
					computer.setDiscontinued( result.getDate("discontinued").toLocalDate() );
				}
				computers.add(computer);
			}
			
		} catch (SQLException e) {
			 System.err.println("Erreur sur la requete find Company by id : "+e.getMessage());
		}finally {
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

		return computers;
	}
	
}
