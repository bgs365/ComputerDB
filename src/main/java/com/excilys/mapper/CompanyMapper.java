package com.excilys.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.excilys.cdb.model.Company;

public class CompanyMapper implements RowMapper<Company>{

	@Override
	public Company mapRow(ResultSet result, int arg1) throws SQLException {
		Company company = new Company();
		company.setId(result.getInt("Id"));
		company.setName(result.getString("Name"));
		return company;
	}
	
}
