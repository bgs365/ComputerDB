package com.excilys.cdb.service;

import java.util.List;

import com.excilys.cdb.dao.CompanyDAO;
import com.excilys.cdb.model.Company;

/**
 * Company service.
 *
 * @author sanogo
 *
 */
public enum CompanyService {
  INSTANCE;

  /**
   * find company by id.
   *
   * @param id asName
   * @return Company
   */
  public Company findById(int id) {
    return CompanyDAO.INSTANCE.findById(id);
  }

  /**
   * find all company.
   *
   * @return List<Company>
   */
  public List<Company> findAll() {
    return CompanyDAO.INSTANCE.findAll();
  }

  /**
   * find and bunch of company to fill page of company.
   *
   * @param pageIndex asName
   * @param numberOfResultByPage asName
   * @return List<Company>
   */
  public List<Company> findLimitNumberOfResult(int pageIndex, int numberOfResultByPage) {
    return CompanyDAO.INSTANCE.findLimitNumberOfResult(pageIndex, numberOfResultByPage);
  }

  /**
   * find company by name.
   *
   * @param name asName
   * @return Company
   */
  public Company findbyName(String name) {
    return CompanyDAO.INSTANCE.findByName(name);
  }

}
