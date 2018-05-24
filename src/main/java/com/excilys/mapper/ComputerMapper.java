package com.excilys.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.RowMapper;

import com.excilys.cdb.dto.ComputerDTO;
import com.excilys.cdb.model.Company;
import com.excilys.cdb.model.Computer;

public class ComputerMapper implements RowMapper<Computer>{

	@Override
	public Computer mapRow(ResultSet result, int arg1) throws SQLException {
		Computer computer = new Computer();
		computer.setId(result.getInt("Id"));
    computer.setName(result.getString("Name"));
    if (result.getDate("introduced") != null) {
      computer.setIntroduced(result.getDate("introduced").toLocalDate());
    }
    if (result.getDate("discontinued") != null) {
      computer.setDiscontinued(result.getDate("discontinued").toLocalDate());
    }
    if (result.getInt("company.Id") != 0) {
    	computer.setCompany(new Company(result.getInt("company.Id"),result.getString("company.name")));
    }
		return computer;
	}
	
	/**
	 * 
	 * @param companies
	 * @return companyDTOs
	 */
	public List<ComputerDTO> mapComputerToComputerDTO(List<Computer> computers) {
		List<ComputerDTO> companyDTOs = new ArrayList<ComputerDTO>();
		for(Computer indexComputer:computers) {
			companyDTOs.add(new ComputerDTO(indexComputer));
		}
		return companyDTOs;
	}

}
