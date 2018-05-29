package com.excilys.cdb.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.RowMapper;

import com.excilys.cdb.dto.CompanyDTO;
import com.excilys.cdb.model.Company;

public class CompanyMapper implements RowMapper<Company>{

	@Override
	public Company mapRow(ResultSet result, int arg1) throws SQLException {
		Company company = new Company();
		company.setId(result.getInt("Id"));
		company.setName(result.getString("Name"));
		return company;
	}
	
	/**
	 * 
	 * @param companies
	 * @return companyDTOs
	 */
	public List<CompanyDTO> mapCompanyToCompanyDTO(List<Company> companies) {
		List<CompanyDTO> companyDTOs= new ArrayList<CompanyDTO>();
		for(Company indexCompany:companies) {
			companyDTOs.add(new CompanyDTO(indexCompany));
		}
		return companyDTOs;
	}
	
}
