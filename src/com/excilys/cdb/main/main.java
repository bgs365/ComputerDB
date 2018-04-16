package com.excilys.cdb.main;


import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.excilys.cdb.dao.Connexion;
import com.excilys.cdb.model.Company;
import com.excilys.cdb.model.Computer;
import com.mysql.jdbc.ResultSetMetaData;
import com.mysql.jdbc.Statement;

public class main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		/*
		Company capcom = new Company(1, "Cap Com");
		Computer computer1 = new Computer(0, "dell 1",LocalDate.of(2017,3,29), LocalDate.of(2017,3,29));
		Computer computer2 = new Computer(0, "dell 1",LocalDate.of(2017,3,29), null);
		System.out.println(computer1);
		System.out.println(computer2);
		/*
		List<Computer> ordiCapcom = new ArrayList<Computer>();
		ordiCapcom.add(computer1);
		ordiCapcom.add(computer2);
		capcom.setComputer(ordiCapcom);
		capcom.addComputer(computer1);
		capcom.addComputer(computer2);
			
		System.out.println(capcom);*/
		
		//Connexion.getConnexion();
		
		Connection conn=Connexion.getConnexion();
		//Création d'un objet Statement
		Statement state = null;
		try {
			state = (Statement) conn.createStatement();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
				e.printStackTrace();
		}
      //L'objet ResultSet contient le résultat de la requête SQL
		ResultSet result = null;
      	ResultSetMetaData resultMeta = null;
      	
		try {
			result = state.executeQuery("SELECT * FROM company");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	      //On récupère les MetaData
	     
		try {
			resultMeta = (ResultSetMetaData) result.getMetaData();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println(result);
		
		/*
		 try {
			while(result.next()){         
			    for(int i = 1; i <= resultMeta.getColumnCount(); i++)
			      System.out.print("\t" + result.getObject(i).toString() + "\t |");
		    
		System.out.println("\n---------------------------------");
		
		  }
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		  try {
			result.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		  try {
			state.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
	}

}
