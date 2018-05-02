package com.excilys.cdb.service;

import java.time.LocalDate;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.excilys.cdb.dao.ComputerDAO;
import com.excilys.cdb.exceptions.ComputerServiceDateException;
import com.excilys.cdb.exceptions.ComputerServiceIllegalExpression;
import com.excilys.cdb.exceptions.ComputerServiceNameTooShortException;
import com.excilys.cdb.main.Main;
import com.excilys.cdb.model.Computer;

/**
 * Computer service.
 *
 * @author sanogo
 *
 */
public enum ComputerService {
  INSTANCE;

  static final Logger LOGGER = LoggerFactory.getLogger(Main.class);

  /**
   * find computer by id.
   *
   * @param id
   *          asName
   * @return Computer
   */
  public Computer findById(int id) {
    return ComputerDAO.INSTANCE.findById(id);
  }

  /**
   * find all computers.
   *
   * @return List<Computer>
   */
  public List<Computer> findAll() {
    return ComputerDAO.INSTANCE.findAll();
  }

  /**
   * find a list of computers by theirs names.
   *
   * @param name
   *          asName
   * @return List<Computer>
   */
  public List<Computer> findByName(String name) {
    return ComputerDAO.INSTANCE.findByName(name);
  }

  /**
   * find a list of computer by their company id.
   *
   * @param id
   *          asName
   * @return List<Computer>
   */
  public List<Computer> findByCompany(int id) {
    return ComputerDAO.INSTANCE.findByCompany(id);
  }

  /**
   * find a list a computer to generate pages.
   *
   * @param pageIndex
   *          asName
   * @param numberOfResultByPage
   *          asName
   * @return List<Computer>
   */
  public List<Computer> findLimitNumberOfResult(int pageIndex, int numberOfResultByPage) {
    return ComputerDAO.INSTANCE.findLimitNumberOfResult(pageIndex, numberOfResultByPage);
  }

  /**
   * Save computer.
   *
   * @param computer
   *          asName
   * @return (0 or 1)
   * @throws ComputerServiceNameTooShortException
   *           asName
   * @throws ComputerServiceIllegalExpression
   *           asName
   * @throws ComputerServiceDateException
   *           asName
   */
  public int save(Computer computer)
      throws ComputerServiceNameTooShortException, ComputerServiceIllegalExpression, ComputerServiceDateException {
    if (verifComputerNameBeforeSave(computer.getName())
        & verifDate(computer.getIntroduced(), computer.getDiscontinued())
        & verifPresenceOfIllegalExpressionBeforeSave(computer.getName())) {
      return ComputerDAO.INSTANCE.save(computer);
    } else {
      LOGGER.info("Name could not be saved");
      return 0;
    }
  }

  /**
   * delete computer.
   *
   * @param id
   *          asName
   * @return (0 or 1)
   */
  public int delete(int id) {
    if (findById(id).getId() == 0) {
      LOGGER.info("Le computer que vous voulez supprimer n'esxiste pas");
      return 0;
    } else {
      return ComputerDAO.INSTANCE.delete(id);
    }
  }

  /**
   * update computer.
   *
   * @param computer
   *          asName
   * @return (0 or 1)
   * @throws ComputerServiceNameTooShortException
   *           asName
   * @throws ComputerServiceIllegalExpression
   *           as Name
   * @throws ComputerServiceDateException
   *           asName
   */
  public int update(Computer computer)
      throws ComputerServiceNameTooShortException, ComputerServiceIllegalExpression, ComputerServiceDateException {

    if (findById(computer.getId()).getId() != 0 & verifComputerNameBeforeSave(computer.getName())
        & verifDate(computer.getIntroduced(), computer.getDiscontinued())
        & verifPresenceOfIllegalExpressionBeforeSave(computer.getName())) {
      return ComputerDAO.INSTANCE.update(computer);
    } else {
      LOGGER.info("Le computer que vous voulez modifier n'esxiste pas");
      return 0;
    }

  }

  /**
   *
   * @param name
   *          asName
   * @return (true or false)
   * @throws ComputerServiceNameTooShortException
   *           asName
   */
  public boolean verifComputerNameBeforeSave(String name) throws ComputerServiceNameTooShortException {
    boolean validComputer = false;
    if ((name.length() < 5)) {
      throw new ComputerServiceNameTooShortException(name);
    } else {
      validComputer = true;
    }
    return validComputer;
  }

  /**
   *
   * @param name
   *          compuerName
   * @return (true or false)
   * @throws ComputerServiceIllegalExpression
   *           asName
   */
  public boolean verifPresenceOfIllegalExpressionBeforeSave(String name) throws ComputerServiceIllegalExpression {
    boolean validComputer = false;
    if (name.contains("<") || name.contains(">") || name.contains("//") || name.contains("\\") || name.contains("/*")
        || name.contains("*/")) {
      throw new ComputerServiceIllegalExpression(name);
    } else {
      validComputer = true;
    }
    return validComputer;
  }

  /**
   *
   * @param introduced
   *          asName
   * @param discounted
   *          asName
   * @return (true or false)
   * @throws ComputerServiceDateException
   *           asName
   */
  public boolean verifDate(LocalDate introduced, LocalDate discounted) throws ComputerServiceDateException {
    boolean validDate = false;
    if (introduced == null && discounted == null) {
      validDate = true;
    } else if (introduced != null & discounted != null) {
      if (introduced.isBefore(discounted)) {
        validDate = true;
      } else {
        throw new ComputerServiceDateException();
      }
    } else if (introduced != null && discounted == null) {
      validDate = true;
    }
    return validDate;
  }

  /**
   * sryy.
   *
   * @param args
   *          asName @throws
   */
  public static void main(String[] args) {

    try {
      System.out.println(ComputerService.INSTANCE.verifComputerNameBeforeSave("argfg"));
      System.out.println(ComputerService.INSTANCE.verifPresenceOfIllegalExpressionBeforeSave("argfg"));
      System.out.println(ComputerService.INSTANCE.verifPresenceOfIllegalExpressionBeforeSave("hu/***/"));
    } catch (ComputerServiceIllegalExpression e) {
      LOGGER.info(e.getMessage());
    } catch (ComputerServiceNameTooShortException e) {
      LOGGER.info(e.getMessage());
    }

  }

}
