package com.excilys.cdb.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.excilys.cdb.dao.CompanyDAO;
import com.excilys.cdb.model.Company;

/**
 * Company service.
 *
 * @author sanogo
 *
 */
@Service
public class CompanyService {
  private CompanyDAO companyDAO;
  
  static final Logger LOGGER = LoggerFactory.getLogger(CompanyService.class);
 
  private CompanyService(CompanyDAO companyDAO) {
  	this.companyDAO = companyDAO;
  }

  /**
   * find company by id.
   *
   * @param id asName
   * @return Company
   */
  public Company findById(int id) {
    return companyDAO.findById(id).get();
  }

  /**
   * find all company.
   *
   * @return List<Company>
   */
  public List<Company> findAll() {
    return companyDAO.findAll();
  }

  /**
   * find and bunch of company to fill page of company.
   *
   * @param pageIndex asName
   * @param numberOfResultByPage asName
   * @return List<Company>
   */
  public List<Company> findLimitNumberOfResult(int pageIndex, int numberOfResultByPage) {
    return companyDAO.findLimitNumberOfResult(pageIndex, numberOfResultByPage);
  }

  /**
   * find company by name.
   *
   * @param name asName
   * @return Company
   */
  public List<Company> findbyName(String name) {
    return companyDAO.findByName(name);
  }
  
  /**
   * delete company.
   *
   * @param id
   *          asName
   * @return (0 or 1)
   */
  public int delete(int id) {
    if (findById(id).getId() == 0) {
      LOGGER.info("Company that you want to delete don't exist");
      return 0;
    } else {
      return companyDAO.delete(id);
    }
  }

}
