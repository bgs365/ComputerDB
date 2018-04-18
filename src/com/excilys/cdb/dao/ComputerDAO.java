package com.excilys.cdb.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.excilys.cdb.model.Company;
import com.excilys.cdb.model.Computer;
import com.mysql.jdbc.PreparedStatement;

public class ComputerDAO {
	
	private static final int NULL = 0;
	String requeteFindById = "SELECT * FROM computer where id = ?";
	String requeteFinfAll = "SELECT * FROM computer";
	String requeteFindByName = "SELECT * FROM computer WHERE name= ? ";
	String requeteFindByCompany = "SELECT * FROM computer WHERE company_id = ?";
	String requeteInsert = "INSERT INTO computer (NAME, INTRODUCED, DISCONTINUED, COMPANY_ID) VALUES (?,?,?,?)";
	String requeteInsertSansCompany = "INSERT INTO computer (NAME, INTRODUCED, DISCONTINUED) VALUES (?,?,?)";
	String requetedelete = "DELETE FROM computer WHERE id = ?";
	String requeteUpdate ="UPDATE computer SET NAME = ? ,INTRODUCED = ? ,DISCONTINUED = ?  WHERE Id = ?";
	String requeteUpdateChangerCompany ="UPDATE computer SET COMPANY_ID = ?  WHERE Id = ?";
	
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
				int companyId = result.getInt("company_id");
				Company company = new CompanyDAO().findById(companyId);
				computer.setCompany( company );
				
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
				int companyId = result.getInt("company_id");
				Company company = new CompanyDAO().findById(companyId);
				computer.setCompany( company );
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
	
	public List<Computer> findByCompany(Company company){
		List<Computer> computers = new ArrayList<Computer>();
		
		Connection conn=Connexion.getConnexion();
		PreparedStatement preparedStatement = null;
		
		try {
			
			preparedStatement = (PreparedStatement) conn.prepareStatement(requeteFindByCompany);
			preparedStatement.setInt(1, company.getId());
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
	
	/**Enregistrement**/
	public void save(Computer computer) {
		Connection conn=Connexion.getConnexion();

			PreparedStatement preparedStatement = null;
			try {
				
				if(computer.getCompany() != null  ) {
					preparedStatement = (PreparedStatement) conn.prepareStatement(requeteInsert);
					preparedStatement.setInt(4, computer.getCompany().getId());
					
				}else {
					preparedStatement = (PreparedStatement) conn.prepareStatement(requeteInsertSansCompany);
				}
				
				preparedStatement.setString(1, computer.getName());
				preparedStatement.setDate(2, java.sql.Date.valueOf( computer.getIntroduced() ) );
				preparedStatement.setDate(3, java.sql.Date.valueOf ( computer.getDiscontinued()) );
				
				// execute insert SQL stetement
				preparedStatement.executeUpdate();

				System.out.println(computer.getName()+" bien créee");

			} catch (SQLException e) {

				//System.out.println(e.getMessage());
				System.out.println(computer.getCompany().getName()+" N'existe pas");

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
	}
	
	/**Supression**/
	public void delete(Computer computer) {
		Connection conn=Connexion.getConnexion();
		PreparedStatement preparedStatement = null;


		try {
			preparedStatement = (PreparedStatement) conn.prepareStatement(requetedelete);
			preparedStatement.setInt(1, computer.getId());

			// execute delete SQL stetement
			preparedStatement.executeUpdate();

			System.out.println(computer.getName()+" a été bien supprimé!");
			
		} catch (SQLException e) {

			System.out.println(e.getMessage());

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
	}
	
	/**Update**/
	public void update(Computer computer) {
		Connection conn=Connexion.getConnexion();
		PreparedStatement preparedStatement = null;


		try {
			preparedStatement = (PreparedStatement) conn.prepareStatement(requeteUpdate);
			
			preparedStatement.setString(1, computer.getName() );
			if(computer.getIntroduced()!=null) {
				preparedStatement.setDate(2, java.sql.Date.valueOf( computer.getIntroduced() ) );
			}else {
				preparedStatement.setDate(2, null );
			}
			if(computer.getDiscontinued()!=null) {
				preparedStatement.setDate(3, java.sql.Date.valueOf ( computer.getDiscontinued()) );
			}else {
				preparedStatement.setDate(3, null );
			}

			preparedStatement.setInt(4, computer.getId());
			preparedStatement.executeUpdate();
			System.out.println(computer.getName()+" a été bien modifié!");
			
		} catch (SQLException e) {
			System.out.println(e.getMessage());
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
	}
	
	/**Update Changer company**/
	public void updateCompany(Computer computer,Company company) {
		Connection conn=Connexion.getConnexion();
		PreparedStatement preparedStatement = null;


		try {
			preparedStatement = (PreparedStatement) conn.prepareStatement(requeteUpdateChangerCompany);
			
			if(company != null ) {
				preparedStatement.setInt(1, company.getId() );
			}else {
				preparedStatement.setInt(1, NULL);
			}

			preparedStatement.setInt(2, computer.getId());
			preparedStatement.executeUpdate();
			System.out.println(computer.getName()+" a bien changé de company!");
			
		} catch (SQLException e) {
			//System.out.println(e.getMessage());
			System.out.println("La company à laquelle vous voulez assigner ce pc n'existe pas");
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
	}
	
	
}
