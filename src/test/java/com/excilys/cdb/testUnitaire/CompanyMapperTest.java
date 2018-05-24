package com.excilys.cdb.testUnitaire;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.excilys.cdb.dto.CompanyDTO;
import com.excilys.cdb.model.Company;
import com.excilys.cdb.springConfig.ApplicationConfig;
import com.excilys.mapper.CompanyMapper;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = ApplicationConfig.class)
@Configuration
public class CompanyMapperTest {
	List<Company> companies = new ArrayList<Company>();

	CompanyMapper companyMapper;
	
	/**
	 * Test .
	 */
	@Test
	public void testMapCompanyToCompanyDTO() {
		companies.add(new Company(1,"a"));
		companies.add(new Company(2,"b"));
		List<CompanyDTO> companiesDTO = companyMapper.mapCompanyToCompanyDTO(companies);
		assertEquals(companies.size(), companiesDTO.size());
	}

}
